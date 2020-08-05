package com.carrier.account.api.service.impl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.carrier.account.api.dao.ICommonDao;
import com.carrier.account.api.jpa.entity.Company;
import com.carrier.account.api.jpa.entity.User;
import com.carrier.account.api.service.ICompany;

/**
 * @author Nanda.Kishore
 *
 */
@Service(value = "company")
public class CompanyServiceImpl implements ICompany<Company, Integer> {

	/**
	 * Initialization of commonDao.
	 */
	@Autowired
	@Qualifier("companyDao")
	private ICommonDao<Company, Integer> commonDao;

	/**
	 * Method to find availability of record.
	 */
	@Override
	public boolean isExists(String str) {
		return false;
	}

	/**
	 * Get entity details.
	 */
	@Override
	public Company getEntity(Integer companyId) {
		return commonDao.getData(companyId);

	}

	/**
	 * create entity.
	 */
	@Override
	public Company createEntity(Company t) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * get array of entity objects.
	 */
	@Override
	public Object[] getEntities(Integer companyId) {
		return null;
	}

	/**
	 * find user record by userid.
	 */
	@Override
	public User findUserByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
    /**
     * Method that checks if mail Already exists.
     */
	@Override
	public boolean isEmailExists(Integer e) {
		// TODO Auto-generated method stub
		return false;
	}
    /**
     * Gets Company Details by ZipCode.
     */
	@Override
	public JSONObject getCompanyByZipCode(String zipCode) {
		return commonDao.getData(zipCode);
	}

}
