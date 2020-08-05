package com.carrier.account.api.service.impl.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.carrier.account.api.service.impl.PendingApprovalsService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PendingApprovalsServiceTest {
	
	@InjectMocks
	private PendingApprovalsService pendingApprovalsService;
	
	@Mock
	private PendingApprovalsService pendingApprovals;
	
	@Mock
	private ResponseStatusException responseStatusException;
	
	@Mock
	private EntityManager entityManager;
	
	@Mock
	private JdbcTemplate jdbcTemplate;
	
	@Mock
	JSONObject jsonObject;
	
	@Mock
    private DataSource ds;
	
	@Mock
	Connection connection;
	
	@Mock
	CallableStatement cstmt;
	
	@Mock
	CallableStatement cstmt1;
	
	@Mock
	ResultSet results;
	
	@BeforeEach
	public void setUp() {
	}
	
	@AfterEach
    void tearDown() throws SQLException {
    }

	@Test(expected = ResponseStatusException.class)
	public void userIdNotHavingAdminAccess() throws SQLException {
		Mockito.when(jdbcTemplate.getDataSource()).thenReturn(ds);
		Mockito.when(ds.getConnection()).thenReturn(connection);
		Mockito.when(connection.prepareCall("{call GetAssignedBlackjackRoleCodes(?)}")).thenReturn(cstmt);
		cstmt.setString(1, "MPOND");
		Mockito.when(cstmt.executeQuery()).thenReturn(results);
		pendingApprovalsService.pendingUserApprovals("MPOND");
	}
	
	@Test
	public void userIdHavingAdminAccess() throws SQLException {
		Mockito.when(jdbcTemplate.getDataSource()).thenReturn(ds);
		Mockito.when(ds.getConnection()).thenReturn(connection);
		Mockito.when(connection.prepareCall("{call GetAssignedBlackjackRoleCodes(?)}")).thenReturn(cstmt);
		cstmt.setString(1, "lmonendo");
		Mockito.when(cstmt.executeQuery()).thenReturn(results);
		Mockito.when(results.next()).thenReturn(true).thenReturn(false);
		Mockito.when(results.getString("RoleName")).thenReturn("RegistrationAdmin");
		Mockito.when(pendingApprovals.adminRoleCheck((Matchers.anyString()))).thenReturn(true);
		Mockito.when(jdbcTemplate.getDataSource()).thenReturn(ds);
		Mockito.when(ds.getConnection()).thenReturn(connection);
		Mockito.when(connection.prepareCall("{call p_Get_SelfRegistrationRequests(?,?,?)}")).thenReturn(cstmt1);
		cstmt1.setString(1, "lmonendo");
		cstmt1.setString(2, "");
		cstmt1.setString(3, "");
		Mockito.when(cstmt1.executeQuery()).thenReturn(results);
		JSONObject result = pendingApprovalsService.pendingUserApprovals("lmonendo");
		assertNotNull(result);
		assertEquals(result.get("count"), 0);
	}

}
