package cBioPortal.service;

import java.sql.Connection;
import java.sql.SQLException;
import org.springframework.web.servlet.ModelAndView;

public interface PropertiesService {

  public int getLastId(Connection conn) throws SQLException;

  public cBioPortal.beans.Properties viewProperties(Connection conn, String id) throws SQLException;

  public ModelAndView getProperties();

  public int insertProperties(Connection conn, String user, String password) 
          throws SQLException;

}
