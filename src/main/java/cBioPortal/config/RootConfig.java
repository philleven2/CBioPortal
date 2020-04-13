// ******************************************************************************
// FILE: RootConfig.java
// AUTHOR: Phil Leven
// CREATED: May 29, 2016
// ******************************************************************************
package cBioPortal.config;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Spring configuration class for the root application context.
 * <p>
 *
 * @author phil leven
 */
@Configuration
@Import({CommonConfig.class, SecurityConfiguration.class})
@ComponentScan(basePackages = "cBioPortal", useDefaultFilters = false,
    includeFilters = {@ComponentScan.Filter(Service.class), @ComponentScan.Filter(Repository.class)})
public class RootConfig {

  /** Logger Object */
  final static Logger log = Logger.getLogger(RootConfig.class.getName());

  @Service
  public static class ApplicationContextService implements ApplicationContextAware {

    private static ApplicationContext appContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

      appContext = applicationContext;

    }

    /**
     * Retrieves the application context.
     *
     * @return The ApplicationContext object.
     */
    public static ApplicationContext getApplicationContext() {

      return appContext;

    }

    /**
     * Retrieves the bean with the specified name from the application context.
     *
     * @param c - The class defining the type of the bean.
     * @param name - The name of the bean
     */
    public static <T extends Object> T getBean(Class<T> c, String name) {

      return appContext.getBean(c, name);

    }

  }

}
