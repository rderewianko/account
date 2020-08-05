package com.carrier.account.api.service;

import java.util.List;

import com.carrier.account.api.jpa.entity.LdapUser;

/**
 * @author Nanda.Kishore
 *
 */
public interface ILdapService {
	/**
	 * Creates user in ldap.
	 * @param ldapUser ldapUser
	 * @return "success/failure"
	 */
	 String create(LdapUser ldapUser);
    /**
     * Updates userdetails in ldap.
     * @param ldapUser ldapUser
     * @return "success/failure"
     */
	String update(LdapUser ldapUser);
    /**
     * Delete user from ldap.
     * @param uid "username"
     * @return "success/failure"
     */
	String delete(String uid);
    /**
     * Search by username in ldap.
     * @param username "username"
     * @return userDetails
     */
	LdapUser findByUserName(String username);
    /**
     * Validates if user exists in ldap.
     * @param uid "username"
     * @return true/false
     */
	boolean isExists(String uid);
	/**
	 * Gets all userdetails from ldap.
	 * @return list
	 */
	List<LdapUser> findAll();

}
