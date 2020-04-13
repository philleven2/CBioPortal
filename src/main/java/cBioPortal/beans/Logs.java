package cBioPortal.beans;

import java.io.Serializable;
import org.apache.log4j.Logger;

public class Logs implements Serializable {

  private final static long serialVersionUID = 1L;

  private static final Logger log = Logger.getLogger(Logs.class.getName());
  
  private String dateCreated;
  private String logger;
  private String level;
  private String message;

  /**
   * Default Constructor.
   */
  public Logs() {

    log.debug("Created instance: " + this.toString());

  }

  public Logs(String dateCreated, String logger, String level, String message) {

    this.dateCreated = dateCreated;
    this.logger = logger;
    this.level = level;
    this.message = message;

  }

  public String getDateCreated() {
    
    return dateCreated;
    
  }

  public void setDateCreated(String dateCreated) {
    
    this.dateCreated = dateCreated;
    
  }

  public String getLogger() {
    
    return logger;
  }
  

  public void setLogger(String logger) {
    
    this.logger = logger;
    
  }

  public String getLevel() {
    
    return level;
    
  }

  public void setLevel(String level) {
    
    this.level = level;
    
  }

  public String getMessage() {
    
    return message;
    
  }

  public void setMessage(String message) {
    
    this.message = message;
    
  }

}
