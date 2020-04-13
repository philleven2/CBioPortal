// ******************************************************************************
// FILE: WebConfig.java
// AUTHOR: Phil Leven
// CREATED: May 29, 2019
// ******************************************************************************
package cBioPortal.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Spring configuration class for the web application context. Replaces dispatcher-servlet.xml
 * <p>
 *
 * @author phil leven
 */
@SuppressWarnings("deprecation")
@Configuration
@EnableWebMvc
@Import({CommonConfig.class, SecurityConfiguration.class})
@ComponentScan(basePackages = "cBioPortal", useDefaultFilters = false,
    includeFilters = {@ComponentScan.Filter(Controller.class), @ComponentScan.Filter(RestController.class)})
public class WebConfig extends WebMvcConfigurerAdapter {

  /** Logger Object */
  final static Logger log = Logger.getLogger(WebConfig.class.getName());

  @Bean
  public ViewResolver viewResolver() {

    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setViewClass(JstlView.class);
    viewResolver.setPrefix("/WEB-INF/views/");
    viewResolver.setSuffix(".jsp");

    return viewResolver;

  }

  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {

    registry.addResourceHandler("/static/**").addResourceLocations("/static/");

  }

}
