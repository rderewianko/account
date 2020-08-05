package com.carrier.account.api.validation;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Nanda.Kishore
 *
 */
@Service(value = "validation")
public class ValidationUtils {

	/**
	 * construction to initialize.
	 */
	public ValidationUtils() {
		//throw new UnsupportedOperationException();
	}

	/**
	 * Validating String parameter.
	 *
	 * @param param param
	 * @return boolean
	 */
	public boolean stringRequestParamValidation(String param) {
		boolean validation = true;
		if (StringUtils.isEmpty(param)) {
			validation = false;
			//throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "zipcode not empty");
		}
		return validation;
	}
	/**
	 * validating companycode parameter.
	 * @param companyCode companyCode
	 * @return boolean
	 */
	public boolean companyCodeRequestValidation(String companyCode) {
		boolean validation = true;
		if (StringUtils.isEmpty(companyCode)) {
			validation = false;
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "companyId not empty");
		} else if (!(companyCode.matches("\\d+"))) {
			validation = false;
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please enter valid companyId");
		}
		return validation;
	}
}
