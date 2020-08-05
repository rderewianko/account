/**
 *
 */
package com.carrier.account.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Component;

import com.carrier.account.api.jpa.entity.LdapUser;
import com.carrier.account.api.jpa.repository.LdapRepo;
import com.carrier.account.api.service.ILdapService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Nanda.Kishore
 *
 */
@Component
@Slf4j
public class LdapServiceImpl implements ILdapService {

	/**
	 * Autowired LdapRepository to make a call to CRUD operations.
	 */
	@Autowired
	private LdapRepo repo;
	/**
	 * Autowired ldapTemplate to call create().
	 */
	@Autowired
	private LdapTemplate ldapTemplate;
    /**
     * Method that handles to create ldapAccount from user provided details.
     */
	@Override
	public String create(LdapUser ldapUser) {
		ldapUser.setDn(LdapNameBuilder.newInstance("uid=" + ldapUser.getUserName()).build());
		ldapTemplate.create(ldapUser);
		log.info("Successfully ldap Account is created");
		return "success";
	}
    /**
     * Updates the Userdetails in ldap.
     */
	@Override
	public String update(LdapUser ldapUser) {
		return null;
	}
    /**
     * Deletes the ldap User.
     */
	@Override
	public String delete(String uid) {
		Optional<LdapUser> user = repo.findOne(LdapQueryBuilder.query().where("uid").is(uid));
		LdapUser ldapUser = user.isPresent() ? user.get() : null;
		if (ldapUser != null) {
			repo.delete(ldapUser);
			return "success";
		}
		return "success";
	}
    /**
     * Reads User details from the ldap.
     */
	@Override
	public LdapUser findByUserName(String userName) {
		Optional<LdapUser> user = repo.findOne(LdapQueryBuilder.query().where("uid").is(userName));
		LdapUser ldapUser = user.isPresent() ? user.get() : null;
		return ldapUser;
	}
    /**
     * Method to check if ldapAccount already exists in ldap or not.
     */
	@Override
	public boolean isExists(String uid) {
		Optional<LdapUser> user = repo.findOne(LdapQueryBuilder.query().where("uid").is(uid));
		LdapUser ldapUser = user.isPresent() ? user.get() : null;
		return ldapUser != null ? true : false;
	}
    /**
     * Method to retrieve all ldap Account.
     */
	@Override
	public List<LdapUser> findAll() {
		Iterable<LdapUser> iterable = repo.findAll();
		List<LdapUser> list = new ArrayList<LdapUser>();
		for (LdapUser user : iterable) {
			list.add(user);
		}
		return list;
	}

}
