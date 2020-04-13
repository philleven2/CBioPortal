package cBioPortal.util;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.log4j.Logger;


public class CBioPortalUtil {

  private static final Logger log = Logger.getLogger(CBioPortalUtil.class.getName());

  /**
   * createHttpClient with RetryHandler
   * 
   * @return
   */
  public static CloseableHttpClient createHttpClient() {

    TrustStrategy trustStrategy = new TrustSelfSignedStrategy();

    SSLContext sslContext = null;

    try {

      sslContext = SSLContexts.custom().loadTrustMaterial(trustStrategy).build();


    } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {

      e.printStackTrace();

    }

    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();

    // Turn off host name verification
    HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;

    // return HttpClients.custom().setRetryHandler((exception, executionCount, context) -> {

    // Avoid Error: Host name 'www.walchconnect.com' does not match the certificate subject provided
    // by the peer (CN=walchconnect.com)
    return HttpClients.custom().setSSLContext(sslContext).setSSLHostnameVerifier(hostnameVerifier)
        .setConnectionManager(connectionManager).setRetryHandler((exception, executionCount, context) -> {

          if (executionCount > 1) {

            log.warn("Maximum tries reached for client http pool ");
            return false;

          }

          if (exception instanceof org.apache.http.NoHttpResponseException) {

            log.warn("No response from server on " + executionCount + " call");
            return true;

          }

          return false;

        }).build();

  }

  /**
   * formatErrorMessage
   * 
   * @param msg
   * @param errMsg
   * @return
   */
  public static String formatErrorMessage(String msg, String errMsg) {

    String message = null;

    StringBuffer strBuf = new StringBuffer();

    // Concatenate msg + errMsg
    strBuf.append(msg).append(errMsg);

    message = strBuf.toString();

    return message;

  } 

}