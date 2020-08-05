package com.carrier.account.api.service.impl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.carrier.account.api.dao.impl.UserDaoImpl;
import com.carrier.account.api.jpa.entity.Company;
import com.carrier.account.api.jpa.entity.LdapUser;
import com.carrier.account.api.jpa.entity.User;
import com.carrier.account.api.jpa.repository.UserRepository;
import com.carrier.account.api.service.impl.LdapServiceImpl;
import com.carrier.account.api.service.impl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private UserDaoImpl userDao;

	@Mock
	private LdapServiceImpl ldapService;

	@BeforeEach
	public void setUp() {
		Mockito.when(userRepository.existsUserByUserId(Matchers.anyString())).thenReturn(true);
	}

	@Test
	public void userNameExists() {
		Mockito.when(userRepository.existsUserByUserId(Matchers.anyString())).thenReturn(true);
		boolean response = userService.isExists("MPOND");
		assertEquals(response, true);
	}

	@Test
	public void userNameNotExists() {
		Mockito.when(userRepository.existsUserByUserId(Matchers.anyString())).thenReturn(false);
		boolean response = userService.isExists("MPONDtestt");
		assertEquals(response, false);
	}

	@Test
	public void emailExists() {
		Mockito.when(userRepository.existsUserByEmailAddress(Matchers.anyString())).thenReturn(true);
		boolean response = userService.isEmailExists("test@gmail.com");
		assertEquals(response, true);
	}

	@Test
	public void emailNotExists() {
		Mockito.when(userRepository.existsUserByEmailAddress(Matchers.anyString())).thenReturn(false);
		boolean response = userService.isEmailExists("test1@gmail.com");
		assertEquals(response, false);
	}

	@Test
	public void createUser() {
		User user = new User();
		//Sets Company Details
		Company company = new Company();
		company.setAddress1("230 East 21st Street");
		company.setAddress2("Suite 300");
		company.setCompanyId(3000007);
		company.setCompanyTypeName("ZENG");
		company.setCompanyTypeCode("Engineering Firm");
		user.setCompany(company);
		//Sets User details
		user.setUsername("test");
		user.setEmailAddress("test@gmail.com");
		user.setFirstName("firstname");
		LdapUser ldapUser= new LdapUser();
		ldapUser.setUserName(user.getUsername());
		ldapUser.setEmailAddress(user.getEmailAddress());
		Mockito.when(userDao.create(user)).thenReturn(Optional.ofNullable(user));
		User userUpdated = userService.createEntity(user);
		assertNotNull(userUpdated);
	}

}
