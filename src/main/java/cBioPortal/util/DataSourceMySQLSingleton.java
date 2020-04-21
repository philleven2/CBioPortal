package cBioPortal.util;

import org.apache.log4j.Logger;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import cBioPortal.resources.CBioPortalBundle;


/**
 * This class creates a Mysql DataSource.
 */
public class DataSourceMySQLSingleton {

  final static Logger log = Logger.getLogger(DataSourceMySQLSingleton.class.getName());

  MysqlDataSource dataSource = null;

  private static DataSourceMySQLSingleton _instance = null;

  protected DataSourceMySQLSingleton() {

    init();

  }

  /**
   * @return The unique instance of this class.
   */
  public static DataSourceMySQLSingleton instance() {

    if (null == _instance) {

      _instance = new DataSourceMySQLSingleton();

    }

    return _instance;

  }

  private void init() {

    try {

      // Create data source
      dataSource = new MysqlDataSource();

      // Set properties
      dataSource.setServerName(CBioPortalBundle.getValueForKey("server.name"));
      dataSource.setDatabaseName(CBioPortalBundle.getValueForKey("database.name"));
      dataSource.setPortNumber(Integer.parseInt(CBioPortalBundle.getValueForKey("port.number")));
      dataSource.setUser(CBioPortalBundle.getValueForKey("user"));
      dataSource.setPassword(CBioPortalBundle.getValueForKey("password"));
      dataSource.setRewriteBatchedStatements(true);
      dataSource.setUseLocalTransactionState(false);
      dataSource.setUseServerPrepStmts(true);
      dataSource.setUseSSL(false);
      dataSource.setMaintainTimeStats(false);

    } catch (Exception e) {

      log.error(e.toString());

    }

  }

  public MysqlDataSource getDataSource() {

    return dataSource;

  }

}