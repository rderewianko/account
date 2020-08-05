package com.carrier.account.api.service;

import org.json.simple.JSONObject;

/**
 * Defines if any company specific related information to be interfaced.
 * @author Nanda.Kishore
 * @param <T>
 * @param <E>
 */
public interface ICompany<T, E> extends ICommonService<T, E> {

	/**
	 * get entity details.
	 * @param zipCode "44444"
	 * @return JSONObject "companyDetails"
	 */
	JSONObject getCompanyByZipCode(String zipCode);
}
