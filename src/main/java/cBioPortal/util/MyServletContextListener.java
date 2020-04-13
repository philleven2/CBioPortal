package cBioPortal.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.zaxxer.hikari.HikariDataSource;

import cBioPortal.resources.CBioPortalBundle;

/*
 * contextInitialized() method will be executed automatically during Servlet container
 * contextDestroyed() method will be executed when the application shuts down
 *
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {

  @Autowired
  ServletContext ctx;

  private static final Logger log = Logger.getLogger(MyServletContextListener.class.getName());
  
  /*
   * When application starts:
   * Test database connection. 
   */
  public void contextInitialized(ServletContextEvent event) {
    
  }

  /*
   * When application stops:
   * 1. Remove and close log4j JDBC appender.
   * 2. Shutdown MySQL abandoned connection cleanup thread.
   * 3. Close Hikari connection pool.
   */
  public void contextDestroyed(ServletContextEvent event) {

    try {

      org.apache.log4j.Logger rootLogger = org.apache.log4j.Logger.getRootLogger();
      
      // Remove and close log4j JDBC appender
      Appender appender = rootLogger.getAppender("DB");
      rootLogger.removeAppender(appender);
      appender.close();
      
      // Shutdown MySQL abandoned connection cleanup thread
      com.mysql.jdbc.AbandonedConnectionCleanupThread.checkedShutdown();

      // Connection pool
      String connPool = CBioPortalBundle.getValueForKey("mysql.connection.pool");

      // If Hikari
      if (connPool.equals("hikari")) {

        // Close Hikari connecion pool
        HikariDataSource pooled = DataSourceHikariSingleton.instance().getPooledDataSource();
        pooled.close();

      }

    } catch (Throwable e) {

      log.error(e);

    }

  }

}