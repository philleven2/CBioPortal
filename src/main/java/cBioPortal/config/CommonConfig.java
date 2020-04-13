// ******************************************************************************
// FILE: CommonConfig.java
// AUTHOR: Phil Leven
// CREATED: May 29, 2019
// ******************************************************************************
package cBioPortal.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Bean definitions shared by both the root and web application contexts.
 * <p>
 *
 * @author phil leven
 */
@Configuration
@EnableAsync
public class CommonConfig {

  /** Logger Object */
  final static Logger log = Logger.getLogger(CommonConfig.class.getName());

  /** Inject property with @Value annotation */
  @Bean(name = "appPropertiesPlaceholderConfigurer")
  public static PropertySourcesPlaceholderConfigurer appPropertiesPlaceholderConfigurer() {

    final PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer =
        new PropertySourcesPlaceholderConfigurer();
    
    propertySourcesPlaceholderConfigurer.setLocation(new ClassPathResource("Howitzer.properties"));
    
    return propertySourcesPlaceholderConfigurer;

  }

}
