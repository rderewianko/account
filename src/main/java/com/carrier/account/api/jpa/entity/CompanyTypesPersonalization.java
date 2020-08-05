package com.carrier.account.api.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *CompanyTypesPersonalization entity.
 *
 */
@Entity
@Table(name = "CompanyTypesPersonalization")
public class CompanyTypesPersonalization {
	/**
	 * Id as column.
	 */
	private int id;
	/**
	 * companyType as column.
	 */
	private String companyType;

	/**
	 * Default constructor.
	 */
	public CompanyTypesPersonalization() {
		super();
	}

	/**
	 * Parameterized constructor.
	 * @param companyType companyType
	 */
	public CompanyTypesPersonalization(String companyType) {
		super();
		this.companyType = companyType;
	}

	/**
	 * get method to return ID.
	 * @return integer
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	/**
	 * setter method to set Id.
	 * @param id id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * get method to return company Type.
	 * @return String
	 */
	public String getCompanyType() {
		return companyType;
	}

	/**
	 * setter method to set companyType.
	 * @param companyType companyType
	 */
	@Column(name = "CompanyType")
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

}
