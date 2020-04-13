package cBioPortal.beans;

import java.io.Serializable;
import org.apache.log4j.Logger;

public class Profiles implements Serializable {

	private final static long serialVersionUID = 1L;

	final static Logger log = Logger.getLogger(Profiles.class.getName());

	private String name;
	private String study;
	private String cancerType;

	/**
	 * Default Constructor.
	 */
	public Profiles() {

		log.debug("Created instance: " + this.toString());

	}

	public Profiles(String name, String study, String cancerType) {

		this.name = name;
		this.study = study;
		this.cancerType = cancerType;

	}

	public String getName() {

		return name;

	}

	public void setName(String name) {

		this.name = name;

	}

	public String getStudy() {

		return study;

	}

	public void setStudy(String study) {

		this.study = study;

	}

	public String getCancerType() {

		return cancerType;

	}

	public void setCancerType(String cancerType) {

		this.cancerType = cancerType;

	}

}
