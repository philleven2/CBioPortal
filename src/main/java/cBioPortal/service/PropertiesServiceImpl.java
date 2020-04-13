package cBioPortal.service;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import cBioPortal.beans.Properties;
import cBioPortal.dao.PropertiesDAO;
import cBioPortal.util.ConnectionUtil;

@Service("propertiesService")
public class PropertiesServiceImpl implements PropertiesService {

  final static Logger log = Logger.getLogger(PropertiesServiceImpl.class.getName());

  // Create PropertiesDAO
  PropertiesDAO propertiesDAO = new PropertiesDAO();

  /**
   * Default Constructor.
   */
  public PropertiesServiceImpl() {

    // set default values

    log.debug("Created instance: " + this.toString());

  }

  @Override
  public int getLastId(Connection conn) throws SQLException {

    int lastId = 0;

    try {

      // Get last Id
      lastId = propertiesDAO.getLastId(conn);

    } catch (SQLException e) {

      throw e;

    }

    return lastId;

  }

  @Override
  public Properties viewProperties(Connection conn, String id) throws SQLException {

    Properties properties = null;

    try {

      // Get Properties
      properties = propertiesDAO.getProperties(conn, id);

    } catch (SQLException e) {

      throw e;

    }

    return properties;

  }

  @Override
  public ModelAndView getProperties() {

    ModelAndView model = new ModelAndView();

    try (Connection conn = ConnectionUtil.getConnection();) {

      // Get properties id
      int id = getLastId(conn);

      // Get properties
      Properties properties = viewProperties(conn, Integer.toString(id));


      model.addObject("user", properties.getUser());
      model.addObject("password", properties.getPassword());
      model.addObject("lastModified", properties.getLastModified());
      model.setViewName("menu");

    } catch (SQLException e) {

      log.error(e.getMessage(), e);

    }

    return model;

  }

  @Override
  public int insertProperties(Connection conn, String user, String password) throws SQLException {

    int cntr = 0;

    try {

      // Insert row
      cntr = propertiesDAO.insertProperties(conn, user, password);

    } catch (SQLException e) {

      throw e;

    }

    return cntr;

  }

}
