package com.carrier.account.api.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserCompanyRolesPersonalization entity.
 *
 */
@Entity
@Table(name = "UserCompanyRolesPersonalization")
public class UserCompanyRolesPersonalization {
	/**
	 * Column ID.
	 */
	private int id;
	/**
	 * CompanyRolesID column - relation CompanyTypes table.
	 */
	private int companyRolesId;
	/**
	 * userId column - relation to users table.
	 */
	private String userId;

	/**
	 * Default constructor.
	 */
	public UserCompanyRolesPersonalization() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Parameterized constructor.
	 * @param companyRolesId companyRolesId
	 * @param userId userId
	 */
	public UserCompanyRolesPersonalization(int companyRolesId, String userId) {
		super();
		this.companyRolesId = companyRolesId;
		this.userId = userId;
	}

	/**
	 * getter method return ID.
	 * @return integer
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	/**
	 * setter method to set id value.
	 * @param id id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * getter method to return companyRolesId.
	 * @return integer
	 */
	@Column(name = "CompanyRolesId")
	public int getCompanyRolesId() {
		return companyRolesId;
	}

	/**
	 * setter method to set companyRolesId.
	 * @param companyRolesId companyRolesId
	 */
	public void setCompanyRolesId(int companyRolesId) {
		this.companyRolesId = companyRolesId;
	}

	/**
	 * getter method to return userid.
	 * @return string
	 */
	@Column(name = "UserId")
	public String getUserId() {
		return userId;
	}

	/**
	 * Setter method to set userId.
	 * @param userId userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
