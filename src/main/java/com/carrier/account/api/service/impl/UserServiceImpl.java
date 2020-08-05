package com.carrier.account.api.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.ldap.NameAlreadyBoundException;
import org.springframework.ldap.NoSuchAttributeException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.carrier.account.api.dao.ICommonDao;
import com.carrier.account.api.jpa.entity.HTMLMail;
import com.carrier.account.api.jpa.entity.LdapUser;
import com.carrier.account.api.jpa.entity.User;
import com.carrier.account.api.jpa.repository.UserRepository;
import com.carrier.account.api.service.ICommonService;
import com.carrier.account.api.service.ILdapService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Nanda.Kishore
 *
 */
@Service(value = "user")
@Slf4j
public class UserServiceImpl implements ICommonService<User, String> {

	/**
	 * Initialization of userrepository.
	 */
	@Autowired
	private UserRepository userRepository;
	/**
	 * Autowires LdapService.
	 */
	@Autowired
	private ILdapService ldapService;
	/**
	 * Initialization of commonDao.
	 */
	@Autowired
	@Qualifier("userDao")
	private ICommonDao<User, Integer> commonDao;
	/**
	 * Initialization of mailService.
	 */
	@Autowired
	@Qualifier("mail")
	private MailService mailService;

	/**
	 * find availability of user record.
	 */
	@Override
	public boolean isExists(String userId) {
		return userRepository.existsUserByUserId(userId);
	}

	/**
	 * get entity.
	 */
	@Override
	public User getEntity(String id) {
		return null;
	}

	/**
	 * create user record in DB. If successful, creates user account in ldap and if
	 * failed/User account already exists in ldap,it rollback's the user record from
	 * DB.
	 */
	@Override
	public User createEntity(User userInfo)  {
		Optional<User> optional = commonDao.create(userInfo);
		if (optional.isPresent()) {
			User user = optional.get();
			LdapUser ldapUser = new LdapUser();
			ldapUser.setUserName(user.getUsername());
			ldapUser.setEmailAddress(user.getEmailAddress());
			ldapUser.setFirstName(user.getFirstName());
			ldapUser.setLastName(user.getLastName());
			ldapUser.setPassword(user.getPassword());
			try {
				String str = ldapService.create(ldapUser);
				if (str == "success") {
					ExecutorService executorService = Executors.newFixedThreadPool(2);

					Set<Runnable> runnables = new HashSet<Runnable>();

					runnables.add(new Runnable() {
					    @Override
						public void run() {
					        mailService.sendAdminRegistrationMail(new HTMLMail(), user);
						}
					});
					runnables.add(new Runnable() {
					    @Override
						public void run() {
					        mailService.sendUserRegistrationMail(new HTMLMail(), user);
					    }
					});
					for (Runnable runnable : runnables) {
						executorService.execute(runnable);
					}
					executorService.shutdown();
				}
				return optional.get();
			} catch (NameAlreadyBoundException alreadyBoundException) {
				log.error(alreadyBoundException.getExplanation());
				commonDao.delete(user.getRequestId());
				throw new ResponseStatusException(HttpStatus.CONFLICT, "UserId:" + ldapUser.getUserName() + " Already Exists in Ldap");
			} catch (NoSuchAttributeException attributeException) {
				log.error(attributeException.getExplanation());
				commonDao.delete(user.getRequestId());
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "One of the attribute passed is not already defined in ldap");
			}
		}
		return userInfo;
	}

	/**
	 * get array of entities.
	 */
	@Override
	public Object[] getEntities(String t) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * find record based on userId.
	 */
	@Override
	public User findUserByUserId(String userId) {
		// TODO Auto-generated method stub
		return userRepository.findUserByUserId(userId);
	}
    /**
     * Methods that checks if mail already exists in DB or not.
     */
	@Override
	public boolean isEmailExists(String email) {
		// TODO Auto-generated method stub
		return userRepository.existsUserByEmailAddress(email);
	}
}
