package cBioPortal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Summary {

	private String molecularAlterationType;
	private String datatype;
	private String name;
	private String description;
	private Boolean showProfileInAnalysisTab;
	private String molecularProfileId;
	private String studyId;
	private Study study;

	public String getMolecularAlterationType() {
	return molecularAlterationType;
	}

	public void setMolecularAlterationType(String molecularAlterationType) {
	this.molecularAlterationType = molecularAlterationType;
	}

	public String getDatatype() {
	return datatype;
	}

	public void setDatatype(String datatype) {
	this.datatype = datatype;
	}

	public String getName() {
	return name;
	}

	public void setName(String name) {
	this.name = name;
	}

	public String getDescription() {
	return description;
	}

	public void setDescription(String description) {
	this.description = description;
	}

	public Boolean getShowProfileInAnalysisTab() {
	return showProfileInAnalysisTab;
	}

	public void setShowProfileInAnalysisTab(Boolean showProfileInAnalysisTab) {
	this.showProfileInAnalysisTab = showProfileInAnalysisTab;
	}

	public String getMolecularProfileId() {
	return molecularProfileId;
	}

	public void setMolecularProfileId(String molecularProfileId) {
	this.molecularProfileId = molecularProfileId;
	}

	public String getStudyId() {
	return studyId;
	}

	public void setStudyId(String studyId) {
	this.studyId = studyId;
	}

	public Study getStudy() {
	return study;
	}

	public void setStudy(Study study) {
	this.study = study;
	}
	
}
