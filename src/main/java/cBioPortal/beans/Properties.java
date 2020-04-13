package cBioPortal.beans;

import java.io.Serializable;
import org.apache.log4j.Logger;

public class Properties implements Serializable {

  private final static long serialVersionUID = 1L;

  final static Logger log = Logger.getLogger(Properties.class.getName());
  
  private String id;
  private String user;
  private String password;
  private String lastModified;

  /**
   * Default Constructor.
   */
  public Properties() {

    log.debug("Created instance: " + this.toString());

  }

  public Properties(String id, String user, String password, String lastModified) {

    this.id = id;
    this.user = user;
    this.password = password;
    this.lastModified = lastModified;

  }

  public String getId() {

    return id;

  }

  public void setId(String id) {

    this.id = id;

  }


  public String getLastModified() {

    return lastModified;

  }

  public void setLastModified(String lastModified) {

    this.lastModified = lastModified;

  }

  public String getUser() {

    return user;

  }

  public void setUser(String user) {

    this.user = user;

  }

  public String getPassword() {

    return password;

  }

  public void setPassword(String password) {

    this.password = password;

  }
  
}
