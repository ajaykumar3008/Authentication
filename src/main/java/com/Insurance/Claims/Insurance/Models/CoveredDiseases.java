package com.Insurance.Claims.Insurance.Models;

public class CoveredDiseases {

	String DiseasesName;
	String ICDCode;
	String description;
	String status;

	public String getDiseasesName() {
		return DiseasesName;
	}

	public void setDiseasesName(String diseasesName) {
		DiseasesName = diseasesName;
	}

	public String getICDCode() {
		return ICDCode;
	}

	public void setICDCode(String iCDCode) {
		ICDCode = iCDCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
