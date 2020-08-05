package com.carrier.account.api.service.impl;

import static com.carrier.account.api.constants.AccountConstants.GET_BRANDS_FOR_COMPANY;
import static com.carrier.account.api.constants.AccountConstants.GET_COMAPNY_ROLES_OF_USER;
import static com.carrier.account.api.constants.AccountConstants.GET_COMAPNY_TYPES_OF_USER;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.InternalServerErrorException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.carrier.account.api.exception.ResourceNotFoundException;
import com.carrier.account.api.jpa.entity.SelfRegistrationRequests;
import com.carrier.account.api.jpa.entity.User;

import lombok.extern.slf4j.Slf4j;

/**
 * Class to find account status and personalization settings.
 *
 */
@Service(value = "accountSettings")
@Slf4j
public class AccountSettingsService {
	/**
	 * Initialization of user service.
	 */
	@Autowired
	@Qualifier("user")
	private UserServiceImpl userService;
	/**
	 * Initialization of user service.
	 */
	@Autowired
	@Qualifier("selfRegrequest")
	private SelfRegistrationRequestsServiceImpl selfRegService;
	/**
	 * Initialization of PendingApprovalsService.
	 */
	@Autowired
	@Qualifier("userRequests")
	private PendingApprovalsService pendingApprovalsService;
	/**
	 * Autowires EntityManager to get DB connection.
	 */
	@Autowired
	private EntityManager entityManager;
	/**
	 * Jdbc template.
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Method to fetch account status and personalization settings.
	 *
	 * @param userId userId
	 * @return settings of user
	 * @throws SQLException SQLException
	 */
	public JSONObject accountSettings(String userId) throws SQLException {
		User user = userService.findUserByUserId(userId);
		JSONObject jsonFinal = new JSONObject();
		JSONArray companyTypes = new JSONArray();
		JSONArray companyRoles = new JSONArray();
		jsonFinal.put("userId", userId);
		if (!StringUtils.isEmpty(user)) {
			log.info("User record available in Users table, userid as : " + userId);
			Query companyTypesQuery = entityManager
					.createNativeQuery(GET_COMAPNY_TYPES_OF_USER);
			companyTypesQuery.setParameter(1, userId);
			List<Object[]> companyTypesResults = companyTypesQuery.getResultList();
			for (Object[] type : companyTypesResults) {
				JSONObject companyType = new JSONObject();
				companyType.put("companyType", (String) type[1]);
				companyTypes.add(companyType);
			}
			Query companyRolesQuery = entityManager
					.createNativeQuery(GET_COMAPNY_ROLES_OF_USER);
			companyRolesQuery.setParameter(1, userId);
			List<Object[]> companyRolesResults = companyRolesQuery.getResultList();
			for (Object[] role : companyRolesResults) {
				JSONObject companyRole = new JSONObject();
				companyRole.put("companyRole", (String) role[1]);
				companyRoles.add(companyRole);
			}
			jsonFinal.put("userStatus", user.getUserStatus());
			jsonFinal.put("firstName", user.getFirstName());
			jsonFinal.put("lastname", user.getLastName());
			jsonFinal.put("companyTypes", companyTypes);
			jsonFinal.put("companyRoles", companyRoles);
			jsonFinal.put("brands", getCompanySupportedBrands(user.getCompanyId()));
			jsonFinal.put("isAdmin", pendingApprovalsService.adminRoleCheck(userId));
		} else {
			SelfRegistrationRequests selfRegResult = selfRegService.findByUserName(userId);
			if (selfRegResult != null) {
				log.info("User record available in SelfRegistrationRequests table, userId as : " + userId);
				jsonFinal.put("userStatus", selfRegResult.getUserStatus());
				jsonFinal.put("lastName", selfRegResult.getUserStatus());
				jsonFinal.put("firstName", selfRegResult.getFirstName());
				jsonFinal.put("companyTypes", companyTypes);
				jsonFinal.put("companyRoles", companyRoles);
				jsonFinal.put("isAdmin", pendingApprovalsService.adminRoleCheck(userId));
			} else {
				log.info("user record not available for userId:" + userId);
				throw new ResourceNotFoundException("UserId:" + userId + " not found");
			}
		}
		return jsonFinal;

	}
	/**
	 * get company supported brands.
	 * @param companyId companyId
	 * @return brands as jsonArray
	 */
	public JSONArray getCompanySupportedBrands(int companyId) {
		Connection connection = null;
		JSONArray listOfBrands = new JSONArray();
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement cstmt = connection.prepareCall(GET_BRANDS_FOR_COMPANY);
			cstmt.setInt(1,  companyId);
			ResultSet results = cstmt.executeQuery();
			while (results.next()) {
				JSONObject brandDetails = new JSONObject();
				brandDetails.put("brandCode", results.getString("BRAND"));
				brandDetails.put("brandName", results.getString("brandName"));
				listOfBrands.add(brandDetails);
			}
			return listOfBrands;
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
}
