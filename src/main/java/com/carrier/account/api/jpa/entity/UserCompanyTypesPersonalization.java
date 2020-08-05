package com.carrier.account.api.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserCompanyTypesPersonalization entity.
 *
 */
@Entity
@Table(name = "UserCompanyTypesPersonalization")
public class UserCompanyTypesPersonalization {
	/**
	 * Id column.
	 */
	private int id;
	/**
	 * companyTypesId column.
	 */
	private int companyTypesId;
	/**
	 * userId column.
	 */
	private String userId;

	/**
	 * Default constructor.
	 */
	public UserCompanyTypesPersonalization() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Parameterized constructor.
	 * @param companyTypesId companyTypesId
	 * @param userId userId
	 */
	public UserCompanyTypesPersonalization(int companyTypesId, String userId) {
		super();
		this.companyTypesId = companyTypesId;
		this.userId = userId;
	}

	/**
	 * get method to return id.
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
	 * getter method to return companyTypesId.
	 * @return integer
	 */
	@Column(name = "CompanyTypesId")
	public int getCompanyTypesId() {
		return companyTypesId;
	}

	/**
	 * setter method to set companyTypesId.
	 * @param companyTypesId companyTypesId
	 */
	public void setCompanyTypesId(int companyTypesId) {
		this.companyTypesId = companyTypesId;
	}

	/**
	 * getter method to return userId.
	 * @return string
	 */
	@Column(name = "UserId")
	public String getUserId() {
		return userId;
	}

	/**
	 * setter method to set userId.
	 * @param userId userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
