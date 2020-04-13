package cBioPortal.config;

import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import cBioPortal.beans.Properties;
import cBioPortal.resources.CBioPortalBundle;
import cBioPortal.service.PropertiesService;
import cBioPortal.util.ConnectionUtil;

/**
 * 
 * Creates a Servlet Filter known as the springSecurityFilterChain which is responsible for all the
 * security (protecting the application URLs, validating submitted username and passwords,
 * redirecting to the log in form, etc) within our application.
 *
 */
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Resource(name = "propertiesService")
  private PropertiesService propertiesService;

  final static Logger log = Logger.getLogger(SecurityConfiguration.class.getName());

  @Autowired
  public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {

    try (Connection conn = ConnectionUtil.getConnection();) {

      // Get properties id
      int id2 = propertiesService.getLastId(conn);

      // Get Clever properties
      Properties properties = propertiesService.viewProperties(conn, Integer.toString(id2));

      // Get user properties
      String user = properties.getUser();
      String password = properties.getPassword();
      String role = CBioPortalBundle.getValueForKey("spring.security.role");

      auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser(user).password(password)
          .roles(role);

    } catch (SQLException e) {

      log.error(e.getMessage());

    }

  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http

        // Access-denied-page: this is the page users will be redirected to when they
        // try to access protected areas.
        .exceptionHandling().accessDeniedPage("/403").and()

        // The intercept-url configuration is where we specify what roles are allowed
        // access to what areas.
        .authorizeRequests().antMatchers("/").access("hasRole('ADMIN')").and()

        // Configure login form.
        .formLogin().loginPage("/login").failureUrl("/login?error").usernameParameter("username")
        .passwordParameter("password").and()

        // Logout is in MainController.java.
        // Configure the logout page.  
        //.logout().logoutSuccessUrl("/login?logout").invalidateHttpSession(true).deleteCookies("JSESSIONID").and()

        // In Spring 4.x upwards we need to explicitly
        // disable CSRF (as below) or alternatively add some HTML to the page to enable CSRF.
        .csrf().disable()

        // session management is used to ensure the user only has one session.
        .sessionManagement().invalidSessionUrl("/login?time=1").maximumSessions(1);

  }

}