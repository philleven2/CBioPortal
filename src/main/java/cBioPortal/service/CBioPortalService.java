package cBioPortal.service;

import java.sql.SQLException;

import cBioPortal.beans.Profiles;

public interface CBioPortalService {

  public Profiles getProfile(String profile) 
		  throws SQLException;
  
}
