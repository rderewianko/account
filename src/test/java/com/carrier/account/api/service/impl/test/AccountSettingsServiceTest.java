package com.carrier.account.api.service.impl.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.carrier.account.api.exception.ResourceNotFoundException;
import com.carrier.account.api.jpa.entity.SelfRegistrationRequests;
import com.carrier.account.api.jpa.entity.User;
import com.carrier.account.api.jpa.entity.UserCompanyTypesPersonalization;
import com.carrier.account.api.service.impl.AccountSettingsService;
import com.carrier.account.api.service.impl.PendingApprovalsService;
import com.carrier.account.api.service.impl.SelfRegistrationRequestsServiceImpl;
import com.carrier.account.api.service.impl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AccountSettingsServiceTest {
	
	@InjectMocks
	private AccountSettingsService accountSettingsService;
	
	@Mock
	private PendingApprovalsService pendingApprovalsService;
	
	@Mock
	private UserServiceImpl userService;
	
	@Mock
	private SelfRegistrationRequestsServiceImpl selfRegService;
	
	@Mock
	private EntityManager entityManager;
	
	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@Mock
	JSONObject jsonObject;
	
	@Mock
	List<Object[]> companyTypesResults;
	
	@Mock
	List<Object[]> companyRolesResults;
	
	@Mock
	User user = new User();
	
	@Mock
	Query companyTypesQuery = mock(Query.class);
	
	@Mock
	Query companyRolesQuery;
	
	@Mock
    private DataSource ds;
	
	@Mock
	Connection connection;
	
	@Mock
	CallableStatement cstmt;
	
	@Mock
	Connection connection1;
	
	@Mock
	CallableStatement cstmt1;
	
	@Mock
	SelfRegistrationRequests selfRegResult = new SelfRegistrationRequests();
	
	@Mock
	ResultSet results;
	
	@Mock
	ResultSet results1;
	
	@BeforeEach
	public void setUp() {
	}
	
	@Test
	public void accountSettingsUserExistsinUsersTable() throws ResponseStatusException, SQLException {
		companyTypesResults  = new ArrayList<>();
		companyRolesResults = new ArrayList<>();
		String companyTypesQueryString = "select uc.Id, ct.CompanyType,uc.UserID from UserCompanyTypesPersonalization uc"
				+ " Inner JOIN CompanyTypesPersonalization ct on ct.Id = uc.CompanyTypesId"
				+ " where uc.UserId = ? ";
		String companyRolesQueryString = "select uc.Id, cr.CompanyRole,uc.UserID from UserCompanyRolesPersonalization uc"
		+ " Inner JOIN CompanyRolesPersonalization cr on cr.Id = uc.CompanyRolesId"
		+ " where uc.UserId = ? ";
		Mockito.when(entityManager.createNativeQuery(companyTypesQueryString))
		  .thenReturn(companyTypesQuery);
		Mockito.when(entityManager.createNativeQuery(companyRolesQueryString))
		  .thenReturn(companyRolesQuery);
		companyTypesQuery.setParameter(1, "MPOND");
		companyRolesQuery.setParameter(1, "MPOND");
		Mockito.when(jdbcTemplate.getDataSource()).thenReturn(ds);
		Mockito.when(ds.getConnection()).thenReturn(connection);
		Mockito.when(connection.prepareCall("{call GetCompanyBrandsName(?)}")).thenReturn(cstmt);
		cstmt.setInt(1, 102);
		Mockito.when(cstmt.executeQuery()).thenReturn(results);
		Mockito.when(jdbcTemplate.getDataSource()).thenReturn(ds);
		Mockito.when(ds.getConnection()).thenReturn(connection);
		Mockito.when(connection.prepareCall("{call GetAssignedBlackjackRoleCodes(?)}")).thenReturn(cstmt1);
		cstmt1.setString(1, "MPOND");
		Mockito.when(cstmt1.executeQuery()).thenReturn(results1);
		//Mockito.when(connection.prepareCall((Matchers.anyString())).thenReturn(cstmt);
		Mockito.when(userService.findUserByUserId(Matchers.anyString())).thenReturn(user);
		JSONObject result = accountSettingsService.accountSettings("MPOND");
		assertNotNull(result);
		assertEquals(result.get("userId"), "MPOND");
	}
	
	@Test
	public void accountSettingsUserNotExistsInUsersTable() throws ResponseStatusException, SQLException {
		Mockito.when(userService.findUserByUserId(Matchers.anyString())).thenReturn(null);
		Mockito.when(selfRegService.findByUserName(Matchers.anyString())).thenReturn(selfRegResult);
		Mockito.when(pendingApprovalsService.adminRoleCheck((Matchers.anyString()))).thenReturn(false);
		Mockito.when(jdbcTemplate.getDataSource()).thenReturn(ds);
		Mockito.when(ds.getConnection()).thenReturn(connection1);
		Mockito.when(connection1.prepareCall("{call GetAssignedBlackjackRoleCodes(?)}")).thenReturn(cstmt1);
		cstmt1.setString(1, "MPOND");
		Mockito.when(cstmt1.executeQuery()).thenReturn(results1);
		JSONObject result = accountSettingsService.accountSettings("MPOND");
		assertNotNull(result);
		assertEquals(result.get("userId"), "MPOND");
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void accountSettingsUserrecordNotFound() throws ResponseStatusException, SQLException {
		Mockito.when(userService.findUserByUserId(Matchers.anyString())).thenReturn(null);
		Mockito.when(selfRegService.findByUserName(Matchers.anyString())).thenReturn(null);
		accountSettingsService.accountSettings("MPOND");
		
	}

}
