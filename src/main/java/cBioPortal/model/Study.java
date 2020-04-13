package cBioPortal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Study {

	private String name;
	private String shortName;
	private String description;
	private Boolean publicStudy;
	private String groups;
	private Integer status;
	private String importDate;
	private String studyId;
	private String cancerTypeId;
	private String referenceGenome;

	public String getName() {
	return name;
	}

	public void setName(String name) {
	this.name = name;
	}

	public String getShortName() {
	return shortName;
	}

	public void setShortName(String shortName) {
	this.shortName = shortName;
	}

	public String getDescription() {
	return description;
	}

	public void setDescription(String description) {
	this.description = description;
	}

	public Boolean getPublicStudy() {
	return publicStudy;
	}

	public void setPublicStudy(Boolean publicStudy) {
	this.publicStudy = publicStudy;
	}

	public String getGroups() {
	return groups;
	}

	public void setGroups(String groups) {
	this.groups = groups;
	}

	public Integer getStatus() {
	return status;
	}

	public void setStatus(Integer status) {
	this.status = status;
	}

	public String getImportDate() {
	return importDate;
	}

	public void setImportDate(String importDate) {
	this.importDate = importDate;
	}

	public String getStudyId() {
	return studyId;
	}

	public void setStudyId(String studyId) {
	this.studyId = studyId;
	}

	public String getCancerTypeId() {
	return cancerTypeId;
	}

	public void setCancerTypeId(String cancerTypeId) {
	this.cancerTypeId = cancerTypeId;
	}

	public String getReferenceGenome() {
	return referenceGenome;
	}

	public void setReferenceGenome(String referenceGenome) {
	this.referenceGenome = referenceGenome;
	}	
}
