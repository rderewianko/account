package com.carrier.account.api.dao.impl;

import static com.carrier.account.api.constants.AccountConstants.CREATE_USER_SP;
import static com.carrier.account.api.constants.AccountConstants.GET_FEATURE_ID_SP;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.InternalServerErrorException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.carrier.account.api.dao.ICommonDao;
import com.carrier.account.api.jpa.entity.Company;
import com.carrier.account.api.jpa.entity.User;

import lombok.extern.slf4j.Slf4j;
/**
 * @author Nanda.Kishore
 * UserDaoImpl that handles user related functionalities in turn that interacts with DB.
 */
@Repository(value = "userDao")
@Slf4j
public class UserDaoImpl implements ICommonDao<User, Integer> {
	/**
	 * Initialization of commonDao.
	 */
	@Autowired
	@Qualifier("companyDao")
	private ICommonDao<Company, Integer> companyDao;
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
	 * Gets Data for UserId.
	 */
	@Override
	public User getData(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Gets Data for zipcode.
	 */
	@Override
	public JSONObject getData(String zipcode) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create a User record in DB.
	 */
	@Override
	public Optional<User> create(User user) {
		//Gets Company Details by calling company
		Company company = companyDao.getData(Integer.valueOf(user.getHvacCompanyId()));
		int featureId = getFeatureId(company.getBrandfamily());
		user.setCompany(company);
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement cstmt = connection.prepareCall(CREATE_USER_SP);
			cstmt.setString("FirstName",  user.getFirstName());
			cstmt.setString("LastName",  user.getLastName());
			cstmt.setString("Email",  user.getEmailAddress());
			cstmt.setString("UserAddress1", company.getAddress1());
			cstmt.setString("UserAddress2", company.getAddress2());
			cstmt.setString("UserName",  user.getUsername());
			cstmt.setString("UserCity",  company.getCity());
			cstmt.setString("UserCountry",  company.getCountry());
			cstmt.setString("UserState",  company.getState());
			cstmt.setString("UserPostalCode",  company.getPostalCode());
			cstmt.setString("UserPhone",  user.getPhoneNumber());
			cstmt.setString("UserFax",  "");
			cstmt.setString("UserCellPhone",  "");
			cstmt.setString("CompanyId",  user.getHvacCompanyId());
			cstmt.setString("UserStatus",  "Pending");
			cstmt.setBoolean("ForwardToInternal", false);
			cstmt.setString("UserExtension",  "");
			cstmt.setString("brandFamily",  company.getBrandfamily());
			cstmt.setInt("FeatureId",  featureId);
			Date date = new Date();
			cstmt.setTimestamp("CreatedDate", new Timestamp(date.getTime()));
			ResultSet results = cstmt.executeQuery();
			while (results.next()) {
				int requestId = results.getInt("Id");
				user.setRequestId(requestId);
				log.info("Request Id:" + requestId);
			}
			cstmt.getMoreResults();
			ResultSet mailIdResultSet = cstmt.getResultSet();
			List<String> listOfEmails = new ArrayList<String>();
			while (mailIdResultSet.next()) {
				listOfEmails.add(mailIdResultSet.getString("Email"));
			}
			user.setMailIds(listOfEmails);
			log.info("User Account Successfully Created in DB");
			return Optional.ofNullable(user);
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
	 * Delete a user record from DB.
	 */
	@Override
	@Transactional
	public Integer delete(Integer id) {
		Query queryForSelfRegistrationOwner = entityManager.createNativeQuery("Delete SelfRegistrationOwner where RequestId=" + id.intValue());
		int statusOfRegistrationOwner = queryForSelfRegistrationOwner.executeUpdate();
		Query queryForSelfRegistrationRequest = entityManager.createNativeQuery("Delete SelfRegistrationRequests where RequestId=" + id.intValue());
		int statusOfRegistrationRequest = queryForSelfRegistrationRequest.executeUpdate();
		return statusOfRegistrationOwner > 0 && statusOfRegistrationRequest > 0 ? new Integer(1) : new Integer(0);
	}
    /**
     * Gets Feature Id for the brandFamily.
     * @param brandFamily "CBP"
     * @return "123"
     */
	public int getFeatureId(String brandFamily) {
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement cstmt = connection.prepareCall(GET_FEATURE_ID_SP);
			cstmt.setString("brandFamily", brandFamily);
			ResultSet results = cstmt.executeQuery();
			results.next();
			int featureId = results.getInt("FeatureId");
			log.info("Brand Family: {} 's featureId is {}", brandFamily, featureId);
			return featureId;
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
