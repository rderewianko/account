package com.carrier.account.api.jpa.entity;

/**
 * @author Nanda.Kishore
 *
 */
public class Brand {

	/**
	 * Brand Name of company.
	 */
	private String brandName;
	/**
	 * Brand Description.
	 */
	private String description;
	/**
	 * parent brand.
	 */
	private String parentBrand;
	/**
	 * catalog value of the brand.
	 */
	private String catalogValue;
	/**
	 * brandfamily.
	 */
	private String brandFamily;

	/**
	 * @return the brandName
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the parentBrand
	 */
	public String getParentBrand() {
		return parentBrand;
	}

	/**
	 * @return the catalogValue
	 */
	public String getCatalogValue() {
		return catalogValue;
	}

	/**
	 * @return the brandFamily
	 */
	public String getBrandFamily() {
		return brandFamily;
	}

	/**
	 * @param brandName the brandName to set.
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	/**
	 * @param description the description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param parentBrand the parentBrand to set.
	 */
	public void setParentBrand(String parentBrand) {
		this.parentBrand = parentBrand;
	}

	/**
	 * @param catalogValue the catalogValue to set.
	 */
	public void setCatalogValue(String catalogValue) {
		this.catalogValue = catalogValue;
	}

	/**
	 * @param brandFamily the brandFamily to set.
	 */
	public void setBrandFamily(String brandFamily) {
		this.brandFamily = brandFamily;
	}
}
