package com.carrier.account.api.jpa.entity;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import lombok.Data;
/**
 * Ldap Entity Class to bind java field to ldap attributes.
 * @author Nanda.Kishore
 *
 */
@Entry(objectClasses = { "inetOrgPerson", "organizationalPerson", "person", "top", "ccsPerson", "carrieruser" })
@Data
public class LdapUser {

	/**
	 * Maps username to ldap's Domain Name attribute.
	 */
	@Id
	private Name dn;
	/**
	 * Maps username field value to uid attribute.
	 */
	@Attribute(name = "uid")
	private String userName;
	/**
	 * Maps firstName field to cn attribute.
	 */
	@Attribute(name = "cn")
	private String firstName;
	/**
	 * Maps lastname field to sn attribute in ldap.
	 */
	@Attribute(name = "sn")
	private String lastName;
	/**
	 * Maps emailAddress field to mail attribute in ldap.
	 */
	@Attribute(name = "mail")
	private String emailAddress;
	/**
	 * Maps userPassword field to userPassword attribute in ldap.
	 */
	@Attribute(name = "userPassword")
	private String password;
}
