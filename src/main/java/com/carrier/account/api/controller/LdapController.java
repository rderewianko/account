/**
 *
 */
package com.carrier.account.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.carrier.account.api.exception.ResourceConflictException;
import com.carrier.account.api.exception.ResourceNotFoundException;
import com.carrier.account.api.jpa.entity.LdapUser;
import com.carrier.account.api.service.ILdapService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Nanda.Kishore
 * This controller is used for dev environment only to verify the ldap details.
 */
@RestController
@RequestMapping("/api")
@Profile("dev")
@Slf4j
public class LdapController {

	/**
	 * Autowires ldapService.
	 */
	@Autowired
	private ILdapService ldapService;

	/**
	 * Gets ldapUser details based on the username.
	 * @param name username
	 * @param req httprequest
	 * @return LdapUser details
	 * @throws ResourceConflictException ResourceConflictException
	 */
	@GetMapping(path = "/ldap/user/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody LdapUser getLdapUserById(@PathVariable(value = "username") String name, HttpServletRequest req)
			throws ResourceConflictException {
		log.info("Requested username for validation: {}", name);
		LdapUser ldapUser = ldapService.findByUserName(name);
		log.info("User Details:" + ldapUser);
		if (ldapUser != null) {
			return ldapUser;
		} else {
			throw new ResourceNotFoundException("Username:" + name + " is not available in Ldap");
		}
	}
    /**
     * Gets all ldapUser details from ldap.
     * @param req httprequest
     * @return list
     * @throws ResourceConflictException ResourceConflictException
     */
	@GetMapping(path = "/ldap/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<LdapUser> getAllLdapUsers(HttpServletRequest req) throws ResourceConflictException {
		return ldapService.findAll();
	}
    /**
     * Creates user details in ldap.
     * @param ldapUser ldapuser
     * @param req request
     * @return success/failure
     * @throws ResourceConflictException ResourceConflictException
     */
	@PostMapping(path = "/ldap/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public String createUser(@RequestBody LdapUser ldapUser, HttpServletRequest req) throws ResourceConflictException {
		String status = ldapService.create(ldapUser);
		log.info("Successfully Created User in Ldap");
		return status;
	}
    /**
     * Delete ldapuser.
     * @param name username
     * @return success/failure
     */
	@DeleteMapping(path = "/ldap/user/{username}")
	public String deleteUser(@PathVariable(value = "username") String name) {
		String status = ldapService.delete(name);
		log.info("Succesfully detlete user in Ldap");
		return status;
	}

}
