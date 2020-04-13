package cBioPortal.util;

import org.apache.log4j.Logger;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import cBioPortal.resources.CBioPortalBundle;


/**
 * This class creates a Hikari DataSource.
 */
public class DataSourceHikariSingleton {

  private static final Logger log = Logger.getLogger(DataSourceHikariSingleton.class.getName());

  HikariDataSource pooled = null;

  private static DataSourceHikariSingleton _instance = null;

  protected DataSourceHikariSingleton() {

    try {
      
      init();
      
    } catch (Exception e) {
      
      return;
      
    }

  }

  /**
   * @return The unique instance of this class.
   */
  public static DataSourceHikariSingleton instance() {

    if (null == _instance) {

      _instance = new DataSourceHikariSingleton();

    }

    return _instance;

  }

  private void init() {

    try {

      StringBuffer strBuf = new StringBuffer();

      // Build jdbc url (jdbc:mysql://localhost:3306/clever_middleware?useSSL=false)
      strBuf.append("jdbc:mysql://").append(CBioPortalBundle.getValueForKey("server.name")).append(":")
          .append(CBioPortalBundle.getValueForKey("port.number")).append("/")
          .append(CBioPortalBundle.getValueForKey("database.name")).append("?useSSL=false");

      HikariConfig config = new HikariConfig();

      config.setDriverClassName("com.mysql.jdbc.Driver");
      config.setJdbcUrl(strBuf.toString());
      config.setUsername(CBioPortalBundle.getValueForKey("user"));
      config.setPassword(CBioPortalBundle.getValueForKey("password"));

      /*
       * autoCommit - This property controls the default auto-commit behavior of connections returned from
       * the pool. It is a boolean value. Default: true
       * 
       * maxLifetime - This property controls the maximum lifetime of a connection in the pool. We
       * strongly recommend setting this value, and it should be several seconds shorter than any database
       * or infrastructure imposed connection time limit. A value of 0 indicates no maximum lifetime
       * (infinite lifetime), subject of course to the idleTimeout setting. Default: 1800000 (30 minutes)
       * 
       * maximumPoolSize - This property controls the maximum size that the pool is allowed to reach,
       * including both idle and in-use connections. When the pool reaches this size, and no idle
       * connections are available, calls to getConnection() will block for up to connectionTimeout
       * milliseconds before timing out. Please read about pool sizing. Default: 10
       * 
       * leakDetectionThreshold - This property controls the amount of time that a connection can be out
       * of the pool before a message is logged indicating a possible connection leak. A value of 0 means
       * leak detection is disabled. Lowest acceptable value for enabling leak detection is 2000 (2
       * seconds). Default: 0
       * 
       * cachePrepStmts - You must set this parameter to true to cache PreparedStatements.
       * 
       * prepStmtCacheSize - This sets the number of prepared statements that the MySQL driver will cache
       * per connection. The default is a conservative 25. We recommend setting this to between 250-500.
       * 
       * prepStmtCacheSqlLimit - This is the maximum length of a prepared SQL statement that the driver
       * will cache. The MySQL default is 256. In our experience, especially with ORM frameworks like
       * Hibernate, this default is well below the threshold of generated statement lengths. Our
       * recommended setting is 2048.
       * 
       */
      config.setAutoCommit(true);
      // config.setMaxLifetime(TimeUnit.SECONDS.toMillis(170));
      config.setMaximumPoolSize(10);
      // config.setLeakDetectionThreshold(TimeUnit.SECONDS.toMillis(170));
      config.addDataSourceProperty("cachePrepStmts", "true");
      config.addDataSourceProperty("prepStmtCacheSize", "250");
      config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
      config.addDataSourceProperty("rewriteBatchedStatements", "true");
      config.addDataSourceProperty("useLocalTransactionState", "false");
      config.addDataSourceProperty("useServerPrepStmts", "true");
      config.addDataSourceProperty("maintainTimeStats", "false");

      pooled = new HikariDataSource(config);

    } catch (Exception e) {

      log.error(e.toString());

    }

  }

  public HikariDataSource getPooledDataSource() {

    if (pooled.isClosed()) {

      try {
        
        init();
        
      } catch (Exception e) {
        
        return null;
        
      }

    }

    return pooled;

  }

}
