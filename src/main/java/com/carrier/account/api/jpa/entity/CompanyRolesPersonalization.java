package com.carrier.account.api.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CompanyRolesPersonalization entity.
 *
 */
@Entity
@Table(name = "CompanyRolesPersonalization")
public class CompanyRolesPersonalization {
	/**
	 * id column.
	 */
	private int id;
	/**
	 * companyRole column.
	 */
	private String companyRole;

	/**
	 * default constructor.
	 */
	public CompanyRolesPersonalization() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Parameterized constructor.
	 * @param companyRole companyRole
	 */
	public CompanyRolesPersonalization(String companyRole) {
		super();
		this.companyRole = companyRole;
	}

	/**
	 * getter method to return id.
	 * @return integer
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	/**
	 * setter method to set id.
	 * @param id id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * getter method to return companyRole.
	 * @return string
	 */
	@Column(name = "CompanyRole")
	public String getCompanyRole() {
		return companyRole;
	}

	/**
	 * setter method to set companyRole.
	 * @param companyRole companyRole
	 */
	public void setCompanyRole(String companyRole) {
		this.companyRole = companyRole;
	}

}
