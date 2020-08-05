package com.carrier.account.api.jpa.entity;

import java.util.List;

/**
 * Class to process personalization settings request details.
 *
 */
public class PersonalizationRequest {
	/**
	 * UserId column.
	 */
	private String userId;
	/**
	 * CompanyTypes as list.
	 */
	private List<String> companyTypes;
	/**
	 * CompanyRoles as list.
	 */
	private List<String> companyRoles;

	/**
	 * getter method to return userId.
	 *
	 * @return string
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * setter method to set userId.
	 *
	 * @param userId userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * getter method to return companyTypes.
	 *
	 * @return list of companyTypes
	 */
	public List<String> getCompanyTypes() {
		return companyTypes;
	}

	/**
	 * set companyTypes.
	 *
	 * @param companyTypes companyTypes
	 */
	public void setCompanyTypes(List<String> companyTypes) {
		this.companyTypes = companyTypes;
	}

	/**
	 * return list of companyRoles.
	 *
	 * @return list of companyRoles
	 */
	public List<String> getCompanyRoles() {
		return companyRoles;
	}

	/**
	 * set companyRoles.
	 *
	 * @param companyRoles companyRoles
	 */
	public void setCompanyRoles(List<String> companyRoles) {
		this.companyRoles = companyRoles;
	}

}
