package cBioPortal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import cBioPortal.beans.Properties;
import cBioPortal.resources.CBioPortalBundle;

public class PropertiesDAO {

  final static Logger log = Logger.getLogger(PropertiesDAO.class.getName());

  /**
   * @param conn
   * @return
   * @throws SQLException
   */
  public int getLastId(Connection conn) throws SQLException {

    int id = 0;

    ResultSet rs = null;

    try (PreparedStatement ps = conn.prepareStatement(CBioPortalBundle.getValueForKey2("get.properties.id"));) {

      // SELECT id FROM Properties
      rs = ps.executeQuery();

      if (rs.next()) {

        id = rs.getInt("id");

      }

    } catch (SQLException e) {

      throw e;

    } finally {

      try {

        if (rs != null) {

          // Close ResultSet
          rs.close();

        }

      } catch (SQLException e) {

        log.error("Error: " + e.getMessage());

      }

    }

    return id;

  }


  /**
   * @param conn
   * @param user
   * @param password
   * @return
   * @throws SQLException
   */
  public int insertProperties(Connection conn, String user, String password) 
          throws SQLException {

    int cntr = 0;

    PreparedStatement ps = null;

    try {

      // INSERT INTO Properties (user, AES_ENCRYPT(password, "Cynthia1")) 
      // VALUES (?, ?)
      ps = conn.prepareStatement(CBioPortalBundle.getValueForKey2("insert.properties"));
      ps.setString(1, user);
      ps.setString(2, password);

      // Insert row
      cntr = ps.executeUpdate();

    } catch (SQLException e) {

      throw e;

    } finally {

      try {

        // Close PreparedStatement
        ps.close();

      } catch (SQLException e) {

        log.error("Error: " + e.getMessage());

      }

    }

    return cntr;

  }

  /**
   * @param conn
   * @param id
   * @return
   * @throws SQLException
   */
  public Properties getProperties(Connection conn, String id) throws SQLException {

    String user = null;
    String password = null;
    String lastModified = null;

    Properties properties = null;

    ResultSet rs = null;

    try (PreparedStatement ps = conn.prepareStatement(CBioPortalBundle.getValueForKey2("get.properties"));) {

      // SELECT user, AES_DECRYPT(password, "Cynthia1") as password, lastModified 
      // FROM Properties WHERE id = ?
      ps.setString(1, id);

      rs = ps.executeQuery();

      if (rs.next()) {

        user = rs.getString("user");
        password = rs.getString("password");
        lastModified = rs.getString("lastModified");

        properties = new Properties(id, user, password, lastModified);

      }

    } catch (SQLException e) {

      throw e;

    } finally {

      try {

        if (rs != null) {

          // Close ResultSet
          rs.close();

        }

      } catch (SQLException e) {

        log.error("Error: " + e.getMessage());

      }

    }

    return properties;

  }

}