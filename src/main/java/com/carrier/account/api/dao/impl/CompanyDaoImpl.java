package com.carrier.account.api.dao.impl;

import static com.carrier.account.api.constants.AccountConstants.GET_COMPANY_BY_ID_SP;
import static com.carrier.account.api.constants.AccountConstants.GET_COMPANY_BY_ZIPCODE_SP;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.InternalServerErrorException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.carrier.account.api.dao.ICommonDao;
import com.carrier.account.api.exception.ResourceNotFoundException;
import com.carrier.account.api.jpa.entity.Brand;
import com.carrier.account.api.jpa.entity.Company;
import com.carrier.account.api.jpa.entity.CompanyServices;

import lombok.extern.slf4j.Slf4j;;

/**
 * ComapanyDao to implement interface methods and process company entity data.
 * @author Nanda.Kishore
 *
 */
@Repository(value = "companyDao")
@Slf4j
public class CompanyDaoImpl implements ICommonDao<Company,  Integer> {

	/**
	 * Jdbc template.
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Method to call stored procedure and get companyDetails.
	 */
	@Override
	public Company getData(Integer companyId) {
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement cstmt = connection.prepareCall(GET_COMPANY_BY_ID_SP);
			cstmt.setInt(1,  companyId);
			boolean results = cstmt.execute();
			if (results) {
				Company company = new Company();
				ResultSet companyDetails = cstmt.getResultSet();
				convertResultSetToCompanyBean(company,  companyDetails,  companyId);
				cstmt.getMoreResults();
				ResultSet resultSet = cstmt.getResultSet();
				convertBrandResultSetToCompany(resultSet, company);
				cstmt.getMoreResults();
				resultSet = cstmt.getResultSet();
				convertCompanySrvcResultSetToCompany(resultSet, company);
				return company;
			}
			throw new ResourceNotFoundException("Company Id:" + companyId + " not found");
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
	 * Converts Company services Results to Company Model Object.
	 * @param resultSet   ResultSetObj
	 * @param company companyObj
	 * @throws SQLException sqlException
	 */
	private void convertCompanySrvcResultSetToCompany(ResultSet resultSet,  Company company)
			throws SQLException {
		List<CompanyServices> listOfCompanyServices = new ArrayList<>();
		while (resultSet.next()) {
			CompanyServices companyServices = new CompanyServices();
			companyServices.setDescription(resultSet.getString("Description"));
			companyServices.setServiceType(resultSet.getString("ServiceType"));
			listOfCompanyServices.add(companyServices);
		}
		resultSet.close();
		if (listOfCompanyServices.isEmpty()) {
			log.warn("Company Services Not identified for the CompanyId:" + company.getCompanyId());
		}
		company.setServices(listOfCompanyServices);
	}

	/**
	 * Converts Brand ResultSet to Company Model Object.
	 * @param resultSet   resultSetObj
	 * @param company  companyObj
	 * @throws SQLException sqlException
	 */
	private void convertBrandResultSetToCompany(ResultSet resultSet,  Company company) throws SQLException {
		List<Brand> listOfBrands = new ArrayList<>();
		while (resultSet.next()) {
			Brand brands = new Brand();
			brands.setBrandName(resultSet.getString("brandName"));
			brands.setDescription(resultSet.getString("Description"));
			brands.setParentBrand(resultSet.getString("parentBrand"));
			brands.setBrandFamily(resultSet.getString("brandFamily"));
			brands.setCatalogValue(resultSet.getString("catalogValue"));
			listOfBrands.add(brands);
		}
		if (listOfBrands.isEmpty()) {
			log.warn("Brands Not identified for the CompanyId:" + company.getCompanyId());
		}
		resultSet.close();
		company.setBrands(listOfBrands);
	}

	/**
	 * Converts ResultSet object to Company Model Object.
	 * @param company        CompanyModelObject
	 * @param companyDetails companyDetailsResultSet
	 * @param companyId      0000100111
	 * @throws SQLException sqlException
	 */
	private void convertResultSetToCompanyBean(Company company,  ResultSet companyDetails,  int companyId)
			throws SQLException {
		if (companyDetails.next()) {
			company.setCompanyId(companyDetails.getInt("CompanyId"));
			company.setCompanyName(companyDetails.getString("CompanyName"));
			company.setLegacyId(companyDetails.getInt("LegacyId"));
			company.setAddress1(companyDetails.getString("Address1"));
			company.setAddress2(companyDetails.getString("Address2"));
			company.setCity(companyDetails.getString("City"));
			company.setPostalCode(companyDetails.getString("PostalCode"));
			company.setState(companyDetails.getString("State"));
			company.setCountry(companyDetails.getString("Country"));
			company.setCompanyTypeCode(companyDetails.getString("CompanyTypeCode"));
			company.setCompanyTypeName(companyDetails.getString("CompanyTypeName"));
			company.setLegacyCompanyTypeCode(companyDetails.getString("LegacyCompanyTypeCode"));
			company.setLegacyCompanyTypeName(companyDetails.getString("LegacyCompanyTypeName"));
			company.setPhone(companyDetails.getString("Phone"));
			company.setEmail(companyDetails.getString("Email"));
			company.setCategoryCode(companyDetails.getString("CategoryCode"));
			company.setDisplayName(companyDetails.getString("DisplayName"));
			company.setCategoryName(companyDetails.getString("CategoryName"));
			company.setIsSoldTo(companyDetails.getString("IsSoldTo"));
			company.setBrandfamily(companyDetails.getString("BrandFamily"));
			company.setIsActive(companyDetails.getInt("IsActive"));
		} else {
			log.error("Company Id:" + companyId + " not found");
			throw new ResourceNotFoundException("Company Id:" + companyId + " not found");
		}
		companyDetails.close();
	}

	/**
	 * Used to Create a Company Details.
	 */
	@Override
	public Optional<Company> create(Company t) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Method to get company details for zipcode.
	 */
	@Override
	public JSONObject getData(String zipcode) {
		Connection connection = null;
		JSONObject companyDetails = new JSONObject();
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement cstmt = connection.prepareCall(GET_COMPANY_BY_ZIPCODE_SP);
			cstmt.setString(1, zipcode);
			boolean results = cstmt.execute();
			log.info("Company results avalible for zipcode request : " + results);
			if (results) {
				JSONArray companyJSONDetails = convertToJSON(cstmt.getResultSet(), zipcode);
				companyDetails.put("CompanyDetails", companyJSONDetails);
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
		return companyDetails;
	}

	/**
	 * Convert a result set into a JSON Array.
	 *
	 * @param resultSet resultset
	 * @param zipcode zipcode
	 * @return a JSONArray
	 * @throws SQLException SQLException
	 */
	public static JSONArray convertToJSON(ResultSet resultSet, String zipcode) throws SQLException {
		JSONArray jsonArray = new JSONArray();
		while (resultSet.next()) {
			int totalRows = resultSet.getMetaData().getColumnCount();
			JSONObject company = new JSONObject();

			company.put("companyId", resultSet.getInt("CompanyId"));
			company.put("companyName", resultSet.getString("CompanyName"));
			company.put("companyAddress", resultSet.getString("CompanyAddress"));
			company.put("city", resultSet.getString("City"));
			company.put("state", resultSet.getString("State"));
			company.put("postalCode", resultSet.getString("PostalCode"));
			company.put("country", resultSet.getString("Country"));
			company.put("companyTypeCode", resultSet.getString("CompanyTypeCode"));
			company.put("companyTypeName", resultSet.getString("CompanyTypeName"));
			company.put("companyCategoryCode", resultSet.getString("CompanyCategoryCode"));
			company.put("companyCategoryName", resultSet.getString("CompanyCategoryName"));
			company.put("isActive", resultSet.getString("IsActive"));
			company.put("brandfamily", resultSet.getString("Brandfamily"));
			if (resultSet.getString("Brandfamily").equalsIgnoreCase("CBP")) {
				company.put("HVACCompanyID", resultSet.getString("HVACCompanyID"));
			} else {
				company.put("HVACCompanyID", resultSet.getString("CRMCompanyID"));
			}
			String brands = resultSet.getString("brands");
			String[] array1 = brands.split(",");

			JSONObject jsonSubObject = null;
			JSONObject brandResult = new JSONObject();
			JSONArray brandArray = new JSONArray();

			for (int i = 0; i < array1.length; i++) {
				jsonSubObject = new JSONObject();
				jsonSubObject.put("brandName", array1[i]);
				// put every object in array
				brandArray.add(jsonSubObject);
			}
			// finally put array in reported jsonobject
			brandResult.put("brands", brandArray);
			company.put("brands", brandArray);
			jsonArray.add(company);
		}
		if (jsonArray.size() == 0) {
			throw new ResourceNotFoundException("Zipcode:" + zipcode + " not found");
		}
		return jsonArray;
	}

	/**
	 * Delete company details from the DB.
	 */
	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}
}
