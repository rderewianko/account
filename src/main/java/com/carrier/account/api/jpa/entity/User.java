	package com.carrier.account.api.jpa.entity;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

/**
 * User entity.
 *
 */
@Entity
@Table(name = "Users")
public class User {
	/**
	 * Id column.
	 */
	private int id;
	/**
	 * userId column.
	 */
	private String userId;
    /**
     * Sets HvacCompanyId.
     */
    @NotBlank(message = "hvacCompanyId is mandatory")
	private String hvacCompanyId;
	/**
	 * Sets Username.
	 */
    @NotBlank(message = "username is mandatory")
	private String username;
	/**
	 * Sets Passsword.
	 */
    @NotBlank(message = "password is mandatory")
	private String password;
	/**
	 * Sets FirstName.
	 */
    @NotBlank(message = "firstName is mandatory")
	private String firstName;
	/**
	 * Sets LastName.
	 */
	private String lastName;
	/**
	 * Sets PhoneNumber.
	 */
	@NotBlank(message = "phoneNumber is mandatory")
	private String phoneNumber;
	/**
	 * Sets EmailAddress.
	 */
	@NotBlank(message = "emailAddress is mandatory")
	private String emailAddress;
	/**
	 * company EmailAddress.
	 */
	private Company company;
	/**
	 * user status.
	 */
	private String userStatus;
	/**
	 * List of MailIds.
	 */
	private List<String> mailIds;
	/**
	 * Sets RequestId.
	 */
	private int requestId;
	/**
	 * companyId column.
	 */
	private int companyId;
	/**
	 * Default user constructor.
	 */
	public User() {
	}

	/**
	 * get id.
	 * @return id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	/**
	 * set id.
	 * @param id id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * get userId.
	 *
	 * @return String
	 */
	@Column(name = "UserID")
	public String getUserId() {
		return userId;
	}
	/**
	 * set userId to userId.
	 * @param userId userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the hvacCompanyId.
	 */
	public String getHvacCompanyId() {
		return hvacCompanyId;
	}
	/**
	 * @return the username
	 */
	@Transient
	public String getUsername() {
		return username;
	}
	/**
	 * @return the password
	 */
	@Transient
	public String getPassword() {
		return password;
	}
	/**
	 * @return the firstName
	 */
	@Column(name = "FirstName")
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @return the lastName
	 */
	@Column(name = "LastName")
	public String getLastName() {
		return lastName;
	}
	/**
	 * @return the phoneNumber
	 */
	@Column(name = "UserPhone")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @return the emailAddress
	 */
	@Column(name = "Email")
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param hvacCompanyId the hvacCompanyId to set
	 */
	public void setHvacCompanyId(String hvacCompanyId) {
		this.hvacCompanyId = hvacCompanyId;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * @return the company
	 */
	@Transient
	public Company getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}
	/**
	 * get user status.
	 * @return string
	 */
	public String getUserStatus() {
		return userStatus;
	}

	/**
	 * set user status.
	 * @param userStatus userStatus
	 */
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	/**
	 * Tostring() method for User entity.
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + "]";
	}

	/**
	 * @return the mailIds
	 */
	@Transient
	public List<String> getMailIds() {
		return mailIds;
	}

	/**
	 * @param mailIds the mailIds to set
	 */
	public void setMailIds(List<String> mailIds) {
		this.mailIds = mailIds;
	}

	/**
	 * @return the requestId
	 */
	@Transient
	public int getRequestId() {
		return requestId;
	}

	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	/**
	 * get companyId of user.
	 * @return integer
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * set companyId of user.
	 * @param companyId companyId
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
}
