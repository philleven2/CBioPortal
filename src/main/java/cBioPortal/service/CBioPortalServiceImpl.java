package cBioPortal.service;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import cBioPortal.beans.Profiles;
import cBioPortal.model.Summary;
import cBioPortal.util.CBioPortalUtil;
import cBioPortal.util.Mapper;
import cBioPortal.util.SimpleCache;

@Service("cBioPortalService")
public class CBioPortalServiceImpl implements CBioPortalService {

	final static Logger log = Logger.getLogger(CBioPortalServiceImpl.class.getName());

	SimpleCache<Profiles> cache = new SimpleCache<Profiles>();
	
	/**
	 * Default Constructor.
	 */
	public CBioPortalServiceImpl() {

		log.debug("Created instance: " + this.toString());

	}

	@Override
	public Profiles getProfile(String profile) throws SQLException {

		Profiles profiles = null;

		try (CloseableHttpClient httpClient = CBioPortalUtil.createHttpClient();) {

			// Get ObjectMapper
			ObjectMapper mapper = Mapper.INSTANCE.getObjectMapper();

			// Socket Timeout – the time waiting for data – after the connection was established; maximum time
			// of inactivity between two data packets.
			// Connection Timeout – the time to establish the connection with the remote host.
			// Connection Manager Timeout – the time to wait for a connection from the connection manager/pool.

			// setExpectContinueEnabled(true) activates 'Expect: 100-Continue' handshake for the entity enclosing methods.
			// sends the request body.

			// The use of the 'Expect: 100-continue' handshake can result in noticeable performance improvement for
			// entity enclosing requests (such as POST and PUT) that require the target server's authentication.

			// Set timeouts to 1 minute
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000)
					.setConnectionRequestTimeout(60000).setExpectContinueEnabled(true).build();

			// Create response
			CloseableHttpResponse response = null;

			// cBioPortal URL
			String url = "https://www.cbioportal.org/api/molecular-profiles/" + profile;

			int retries = 0;

			String text = null;

			do {

				// Is request already cached
				profiles = cache.get(profile);
				
				// If cached request found
				if (profiles != null) {
					
					break;
					
				}
				
				// Call CBioPortal REST API
				HttpGet httpGet = new HttpGet(url);

				// Set timeouts to 1 minute
				httpGet.setConfig(requestConfig);

				try {

					response = httpClient.execute(httpGet);

				} catch (IOException e) {

					// Insert CleverSynchError
					text = CBioPortalUtil.formatErrorMessage("Error: IO Exception calling CBioPortal. ", e.toString());

					log.error(text);

					// Wait 5 seconds
					try {

						Thread.sleep(5000);

					} catch (InterruptedException e1) {

						e1.printStackTrace();

					}

					// Increment retries
					retries++;

					// If more than 3 retries
					if (retries > 3) {

						profiles = new Profiles("Error: " + e.getMessage(), "", "");

					// If less than 3 retries - retry again
					} else {

						continue;

					}

				}

				// Get response status
				int statusCode = response.getStatusLine().getStatusCode();

				// Response to string
				// InputStream is = response.getEntity().getContent();
				// StringWriter writer = new StringWriter();
				// IOUtils.copy(is, writer, "UTF-8");
				// String result = writer.toString();

				// System.out.println(result);

				// If response status != 200
				if (statusCode != 200) {

					// Log error
					text = CBioPortalUtil.formatErrorMessage("Error: Calling CBioPortal.  Response status = ",
							Integer.toString(statusCode));

					log.error(text);

					// Read the response content and close the stream
					EntityUtils.consume(response.getEntity());
					response.close();

					profiles = new Profiles("Error: " + text, "", "");

				// If response status = 200
				} else {

					log.info("Get Profile.");

					HttpEntity httpEntity = response.getEntity();

					// Convert JSON to object
					Summary summary = (Summary) mapper.readValue(httpEntity.getContent(), Summary.class);

					// Read the response content and close the stream
					EntityUtils.consume(httpEntity);
					response.close();

					String name = summary.getName();
					String studyName = summary.getStudy().getName();
					String cancerTypeId = summary.getStudy().getCancerTypeId();

					profiles = new Profiles(name, studyName, cancerTypeId);

					// Add to cache
					cache.put(profile, profiles);
					
				} // If response status = 200

				break;

			} while (1 == 1);

		} catch (IOException e) {

			log.error("Error: " + e.getMessage());

		}

		return profiles;

	}

}
