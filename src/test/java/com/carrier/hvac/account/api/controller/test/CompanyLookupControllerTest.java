package com.carrier.hvac.account.api.controller.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import com.carrier.account.api.controller.CompanyLookupController;
import com.carrier.account.api.jpa.entity.Company;
import com.carrier.account.api.service.ICompany;
import com.carrier.account.api.validation.ValidationUtils;

@RunWith(MockitoJUnitRunner.class)
public class CompanyLookupControllerTest {

	@InjectMocks
	private CompanyLookupController companyLookupController;

	@Mock
	private ICompany<Company, Integer> companyService;

	@Mock
	private ValidationUtils validationUtils;

	@Mock
	Company output;

	@Mock
	JSONObject jsonObject;

	@BeforeEach
	public void setup() {
	}

	@Test(expected = ResponseStatusException.class)
	public void ComapnyCodeNotFound() throws Exception {
		Mockito.when(validationUtils.companyCodeRequestValidation(Matchers.anyString())).thenThrow(ResponseStatusException.class);
		Object responseObject = companyLookupController.companyCodeLookup("");
	}

	@Test(expected = ResponseStatusException.class)
	public void ComapnyCodeNotFoundAsString() throws Exception {
		Mockito.when(validationUtils.companyCodeRequestValidation(Matchers.anyString())).thenThrow(ResponseStatusException.class);
		Object responseObject = companyLookupController.companyCodeLookup("fgd56");
	}

	@Test
	public void companyCodeValid() throws Exception {
		output = new Company();
		output.setCompanyId(3000005);
		Mockito.when(validationUtils.companyCodeRequestValidation(Matchers.anyString())).thenReturn(true);
		Mockito.when(companyService.getEntity(Matchers.anyInt())).thenReturn(output);
		Company responseObject = (Company) companyLookupController.companyCodeLookup("3000005");
		assertNotNull(responseObject);
		assertEquals(responseObject.getCompanyId(), 3000005);
	}

	@Test(expected = ResponseStatusException.class)
	public void zipcodeEmpty() throws Exception {
		Mockito.when(validationUtils.stringRequestParamValidation(Matchers.anyString())).thenThrow(ResponseStatusException.class);
		Object responseObject = companyLookupController.zipCodeLookup("");
	}

	@Test
	public void zipCodeValid() throws Exception {
		output = new Company();
		output.setCompanyId(3000005);
		Mockito.when(validationUtils.stringRequestParamValidation(Matchers.anyString())).thenReturn(true);
		Mockito.when(companyService.getCompanyByZipCode(Matchers.anyString())).thenReturn(jsonObject);
		JSONObject responseObject = companyLookupController.zipCodeLookup("98052");
		assertNotNull(responseObject);
		assertEquals(responseObject.toString(), jsonObject.toString());
	}


}
