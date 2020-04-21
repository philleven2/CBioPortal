package cBioPortal.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cBioPortal.beans.Profiles;
import cBioPortal.service.CBioPortalService;
import cBioPortal.service.LogsService;
import cBioPortal.util.CBioPortalUtil;

@Controller
public class MainController {

  @Resource(name = "cBioPortalService")
  private CBioPortalService cBioPortalService;

  @Resource(name = "logsService")
  private LogsService logsService;
  final static Logger log = Logger.getLogger(MainController.class.getName());
  
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView login(@RequestParam(value = "error", required = false) String error,
      @RequestParam(value = "logout", required = false) String logout) {

    ModelAndView model = new ModelAndView();

    if (error != null) {

      model.addObject("error", "Invalid username and password!");

    }

    if (logout != null) {

      model.addObject("msg", "You've been logged out successfully.");

    }

    model.setViewName("login");
    return model;

  }

  @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
  public String logout(HttpServletRequest request, HttpServletResponse response) {
    
    HttpSession session = request.getSession(false);
    SecurityContextHolder.clearContext();
    session = request.getSession(false);
    
    if (session != null) {
      
        session.invalidate();
        
    }
    
    for (Cookie cookie : request.getCookies()) {
      
        cookie.setMaxAge(0);
        
    }

    return "logout";
      
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView showMenu() {

    ModelAndView model = null;
    
    try {

    model = new ModelAndView("menu");

    } catch (Exception e) {

      log.error(e.getMessage(), e);

      model = new ModelAndView("menu");
      model.addObject("msg", e);
      return model;

    }

    return model;

  }

  @RequestMapping(value = "/back", method = RequestMethod.GET)
  public ModelAndView backShowMenu() {

    return showMenu();

  }

  @RequestMapping(value = "/getProfile", method = RequestMethod.GET)
  public ModelAndView getProfile(HttpServletRequest req) {

    ModelAndView model = new ModelAndView("menu");
    
    try (CloseableHttpClient httpClient = CBioPortalUtil.createHttpClient();) {

      String profile = req.getParameter("profile");
    	
      // Get profile
      Profiles profiles = cBioPortalService.getProfile(profile);
      
      // Parse name
      String name = profiles.getName();
      
      // If error
      if (name.contains("Error")) {
    	  
          model.addObject("msg", name);
          return model;
    	  
      }

      model.addObject("selProfile", profile);
      model.addObject("profiles", profiles);
      
    } catch (SQLException | IOException e) {
      
      e.printStackTrace();
      
    }
    
    return model;

  }

}
