package com.carrier.account.api.service.impl;

import static com.carrier.account.api.constants.AccountConstants.GET_ASSIGNED_BLACKJACK_ROLE;
import static com.carrier.account.api.constants.AccountConstants.GET_SELF_REGISTRATION_REQUESTS;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.InternalServerErrorException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;

/**
 * Service to fetch list self registration user requests for userId.
 *
 */
@Service(value = "userRequests")
@Slf4j
public class PendingApprovalsService {
	/**
	 * Jdbc template.
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * method to check user role and return list of pending user approvals.
	 *
	 * @param userId userId
	 * @return list of pending approvals
	 */
	public JSONObject pendingUserApprovals(String userId) {
		Connection connection = null;
		JSONObject company = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			if (adminRoleCheck(userId)) {
				CallableStatement cstmt = connection.prepareCall(GET_SELF_REGISTRATION_REQUESTS);
				cstmt.setString("LoginUserID", userId);
				cstmt.setString("BrandFamily", "");
				cstmt.setString("whereCondition", "");
				ResultSet results = cstmt.executeQuery();
				int count = 0;
				while (results.next()) {
					int totalRows = results.getMetaData().getColumnCount();
					count = count + 1;
					JSONObject userDetails = new JSONObject();

					for (int i = 0; i < totalRows; i++) {
						userDetails.put(results.getMetaData().getColumnLabel(i + 1),
								results.getObject(i + 1));
					}
					jsonArray.add(userDetails);
				}
				log.info("approval user requests count:" + count);
				company.put("approvalUsers", jsonArray);
				company.put("count", count);
				return company;
			} else {
				log.info("User dont have admin access : access denied");
				throw new ResponseStatusException(HttpStatus.FORBIDDEN, "access denied");
			}
		} catch (SQLException ex) {
			log.error(ex.getMessage());
			throw new InternalServerErrorException("Internal Server Error");
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqlExcep) {
					log.error(sqlExcep.getMessage());
					throw new InternalServerErrorException("Internal Server Error");
				}
			}
		}
	}

	/**
	 * method check if user is administrator or not.
	 * @param userId     userId
	 * @return true/false
	 * @throws SQLException SQLException
	 */
	public boolean adminRoleCheck(String userId) throws SQLException {
		boolean adminCheck = false;
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement cstmt = connection.prepareCall(GET_ASSIGNED_BLACKJACK_ROLE);
			cstmt.setString("UserName", userId);
			ResultSet results = cstmt.executeQuery();
			while (results.next()) {
				String roleName = results.getString("RoleName");
				if (results.getString("RoleName").equalsIgnoreCase("RegistrationAdmin")) {
					adminCheck = true;
					log.info("Admin access : User have admin access to get pending approval users");
				}
			}
		} catch (SQLException ex) {
			log.error(ex.getMessage());
			throw new InternalServerErrorException("Internal Server Error");
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqlExcep) {
					log.error(sqlExcep.getMessage());
					throw new InternalServerErrorException("Internal Server Error");
				}
			}
		}
		return adminCheck;
	}

}
