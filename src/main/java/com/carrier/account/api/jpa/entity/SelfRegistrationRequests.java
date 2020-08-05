package com.carrier.account.api.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * SelfRegistrationRequests entity.
 */
@Entity
@Table(name = "SelfRegistrationRequests")
public class SelfRegistrationRequests {
	/**
	 * Id column.
	 */
	private int requestId;
	/**
	 * userStatus column.
	 */
	private String userStatus;
	/**
	 * userName column.
	 */
	private String userName;
	/**
	 * get requestId.
	 * @return integer
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getRequestId() {
		return requestId;
	}
	/**
	 * firstName of user.
	 */
	private String firstName;
	/**
	 * lastName of user.
	 */
	private String lastName;
	/**
	 * Set requestId .
	 * @param requestId requestId
	 */
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	/**
	 * Get user status.
	 * @return String
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
	 * returns userName.
	 * @return String
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * set userName.
	 * @param userName userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * get firstName of user.
	 * @return String
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * set firstName of user.
	 * @param firstName firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * get lastName of user.
	 * @return String
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * set lastName of user.
	 * @param lastName lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
