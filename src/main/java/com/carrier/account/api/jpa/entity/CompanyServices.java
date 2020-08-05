package com.carrier.account.api.jpa.entity;

/**
 * @author Nanda.Kishore This class is a model object that carries company
 *         services details of the company
 */
public class CompanyServices {
	/**
	 * serviceType of the company.
	 */
	private String serviceType;
	/**
	 * Description of the companyService.
	 */
	private String description;

	/**
	 * @return the serviceType
	 */
	public String getServiceType() {
		return serviceType;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param serviceType the serviceType to set
	 */
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
