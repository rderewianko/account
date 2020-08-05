package com.carrier.account.api.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.carrier.account.api.jpa.entity.Company;
import com.carrier.account.api.service.ICompany;
import com.carrier.account.api.validation.ValidationUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller to process company lookup by companyId and Zipcode.
 *
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class CompanyLookupController {

	/**
	 * Initialization of companyService.
	 */
	@Autowired
	@Qualifier("company")
	private ICompany<Company, Integer> companyService;
	/**
	 * Initialization of validationUtils.
	 */
	@Autowired
	@Qualifier("validation")
	private ValidationUtils validationUtils;

	/**
	 * Method to process request company lookup by comapnyId.
	 *
	 * @param companyId companyId
	 * @return entity Object
	 */
	@GetMapping(path = "/company", params = "companyId", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object companyCodeLookup(@RequestParam(value = "companyId") String companyId) {
		log.info("Company lookup - CompanyId request parameter : " + companyId);
		if (validationUtils.companyCodeRequestValidation(companyId)) {
			return companyService.getEntity(Integer.parseInt(companyId));
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "companyId not empty");
		}
	}
	/**
	 * Method to process request company lookup by zipcode.
	 * @param zipCode zipCode
	 * @return entity object
	 */
	@GetMapping(path = "/company", params = "zipcode", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JSONObject zipCodeLookup(@RequestParam(value = "zipcode") String zipCode) {
		log.info("Company lookup - zipCode request parameter : " + zipCode);
		if (validationUtils.stringRequestParamValidation(zipCode)) {
			return companyService.getCompanyByZipCode(zipCode);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "zipcode not empty");
		}
	}
}
