// ******************************************************************************
// FILE: AppInitializer.java
// AUTHOR: Phil Leven
// CREATED: May 29, 2019
// ******************************************************************************
package cBioPortal.config;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Bootstraps the application. This class replaces web.xml.
 * <p>
 *
 * @author phil leven
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  /** Logger Object */
  final static Logger log = Logger.getLogger(AppInitializer.class.getName());

  /**
   * Specify the @Configuration and/or @Component classes which should be provided to the root
   * application context.
   */
  @Override
  protected Class<?>[] getRootConfigClasses() {

    return new Class[] {RootConfig.class};

  }

  /**
   * Specify the @Configuration and/or @Component classes which should be provided to the dispatcher
   * servlet application context.
   */
  @Override
  protected Class<?>[] getServletConfigClasses() {

    return new Class[] {WebConfig.class};

  }

  /**
   * Specify the servlet mappings for the DispatcherServlet
   */
  @Override
  protected String[] getServletMappings() {

    return new String[] {"/"};

  }

}
