package com.carrier.account.api.jpa.entity;

import java.util.List;

/**
 * @author Nanda.Kishore
 *
 */
public class Company {
	/**
	 * CompanyId.
	 */
	private int companyId;
	/**
	 * CompanyName.
	 */
	private String companyName;
	/**
	 * Address 1.
	 */
	private String address1;
	/**
	 * Address 2.
	 */
	private String address2;
	/**
	 * City.
	 */
	private String city;
	/**
	 * State.
	 */
	private String state;
	/**
	 * Country.
	 */
	private String country;
	/**
	 * postalCode.
	 */
	private String postalCode;
	/**
	 * Phone.
	 */
	private String phone;
	/**
	 * Email.
	 */
	private String email;
	/**
	 * CompanyTypeCode.
	 */
	private String companyTypeCode;
	/**
	 * CompanyTypeName.
	 */
	private String companyTypeName;
	/**
	 * LegacyId.
	 */
	private int legacyId;
	/**
	 * legacyCompanyTypeCode.
	 */
	private String legacyCompanyTypeCode;
	/**
	 * LegacyCompanyTypeName.
	 */
	private String legacyCompanyTypeName;
	/**
	 * CategoryCode.
	 */
	private String categoryCode;
	/**
	 * DisplayName.
	 */
	private String displayName;
	/**
	 * CategoryName.
	 */
	private String categoryName;
	/**
	 * isSoldTo.
	 */
	private String isSoldTo;
	/**
	 * IsActive.
	 */
	private int isActive;
	/**
	 * BrandFamily.
	 */
	private String brandfamily;
	/**
	 * Brands.
	 */
	private List<Brand> brands;
	/**
	 * CompanyServices.
	 */
	private List<CompanyServices> services;

	/**
	 * @return the services
	 */
	public List<CompanyServices> getServices() {
		return services;
	}

	/**
	 * @param services the services to set.
	 */
	public void setServices(List<CompanyServices> services) {
		this.services = services;
	}

	/**
	 * @return the legacyId
	 */
	public int getLegacyId() {
		return legacyId;
	}

	/**
	 * @return the legacyCompanyTypeCode
	 */
	public String getLegacyCompanyTypeCode() {
		return legacyCompanyTypeCode;
	}

	/**
	 * @return the legacyCompanyTypeName
	 */
	public String getLegacyCompanyTypeName() {
		return legacyCompanyTypeName;
	}

	/**
	 * @return the categoryCode
	 */
	public String getCategoryCode() {
		return categoryCode;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @return the companyId
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the companyTypeCode
	 */
	public String getCompanyTypeCode() {
		return companyTypeCode;
	}

	/**
	 * @return the companyTypeName
	 */
	public String getCompanyTypeName() {
		return companyTypeName;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @return the isSoldTo
	 */
	public String getIsSoldTo() {
		return isSoldTo;
	}

	/**
	 * @return the isActive
	 */
	public int getIsActive() {
		return isActive;
	}

	/**
	 * @return the brandfamily
	 */
	public String getBrandfamily() {
		return brandfamily;
	}

	/**
	 * @return the brands
	 */
	public List<Brand> getBrands() {
		return brands;
	}

	/**
	 * @param companyId the companyId to set.
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	/**
	 * @param companyName the companyName to set.
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @param address1 the address1 to set.
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @param address2 the address2 to set.
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @param city the city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @param state the state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @param country the country to set.
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @param postalCode the postalCode to set.
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @param phone the phone to set.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @param email the email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param companyTypeCode the companyTypeCode to set.
	 */
	public void setCompanyTypeCode(String companyTypeCode) {
		this.companyTypeCode = companyTypeCode;
	}

	/**
	 * @param companyTypeName the companyTypeName to set.
	 */
	public void setCompanyTypeName(String companyTypeName) {
		this.companyTypeName = companyTypeName;
	}

	/**
	 * @param legacyId the legacyId to set.
	 */
	public void setLegacyId(int legacyId) {
		this.legacyId = legacyId;
	}

	/**
	 * @param legacyCompanyTypeCode the legacyCompanyTypeCode to set.
	 */
	public void setLegacyCompanyTypeCode(String legacyCompanyTypeCode) {
		this.legacyCompanyTypeCode = legacyCompanyTypeCode;
	}

	/**
	 * @param legacyCompanyTypeName the legacyCompanyTypeName to set.
	 */
	public void setLegacyCompanyTypeName(String legacyCompanyTypeName) {
		this.legacyCompanyTypeName = legacyCompanyTypeName;
	}

	/**
	 * @param categoryCode the categoryCode to set.
	 */
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	/**
	 * @param displayName the displayName to set.
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @param categoryName the categoryName to set.
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @param isSoldTo the isSoldTo to set.
	 */
	public void setIsSoldTo(String isSoldTo) {
		this.isSoldTo = isSoldTo;
	}

	/**
	 * @param isActive the isActive to set.
	 */
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	/**
	 * @param brandfamily the brandfamily to set.
	 */
	public void setBrandfamily(String brandfamily) {
		this.brandfamily = brandfamily;
	}

	/**
	 * @param brands the brands to set.
	 */
	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

}