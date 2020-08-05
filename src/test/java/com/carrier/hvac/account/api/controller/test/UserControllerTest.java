
package com.carrier.hvac.account.api.controller.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.carrier.account.api.controller.UserController;
import com.carrier.account.api.exception.ResourceConflictException;
import com.carrier.account.api.jpa.entity.Company;
import com.carrier.account.api.jpa.entity.PersonalizationRequest;
import com.carrier.account.api.jpa.entity.User;
import com.carrier.account.api.response.MessageResponse;
import com.carrier.account.api.service.ICommonService;
import com.carrier.account.api.service.impl.AccountSettingsService;
import com.carrier.account.api.service.impl.OktaService;
import com.carrier.account.api.service.impl.PendingApprovalsService;
import com.carrier.account.api.service.impl.PersonalizationSettingsService;
import com.carrier.account.api.validation.ValidationUtils;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@InjectMocks
	private UserController userController;

	@Mock
	private ICommonService<User, Integer> userService;
	
	@Mock
	private OktaService oktaService;

	@Mock
	private PersonalizationSettingsService PersonalizationSettingsService;
	@Mock
	private PendingApprovalsService pendingApprovals;

	@Mock
	ResponseEntity responseEntity;

	@Mock
	HttpServletRequest req;

	@Mock
	List<String> types;

	@Mock
	List<String> roles;

	@Mock
	ResponseStatusException responseStatusException;

	@Mock
	ResponseStatusException response;

	@Mock
	PersonalizationRequest request = new PersonalizationRequest();

	@Mock
	ResponseEntity<Object> result;
	
	@Mock
	private ValidationUtils validationUtils;
	
	@Mock
	AccountSettingsService accountSettings;
	
	@Mock
	JSONObject jsonObject;

	@BeforeEach
	public void setup() {
		Mockito.when(userService.isExists((Matchers.anyString()))).thenReturn(true);
	}

	@Test(expected = ResourceConflictException.class)
	public void usernameConflicts() throws Exception {
		Mockito.when(userService.isExists((Matchers.anyString()))).thenReturn(true);
		Mockito.when(oktaService.isUserameAvailable(Matchers.anyString())).thenReturn(true);
		responseEntity.status(HttpStatus.CONFLICT);
		ResponseEntity responseObject = userController.getUserById("MPOND", req);
		assertEquals(responseObject.getStatusCode(), 409);
	}

	@Test
	public void usernameValid() throws Exception {
		Mockito.when(userService.isExists((Matchers.anyString()))).thenReturn(false);
		Mockito.when(oktaService.isUserameAvailable(Matchers.anyString())).thenReturn(true);
		responseEntity.status(HttpStatus.OK);
		ResponseEntity responseObject = userController.getUserById("testUser", req);
		assertEquals(responseObject.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void createUser() throws Exception {
		User user = new User();
		user.setFirstName("test");
		user.setLastName("test1");
		userService.createEntity(user);
		Mockito.when(userService.createEntity(user)).thenReturn(user);
		Mockito.when(oktaService.createOktaUser(user)).thenReturn(true);
		ResponseEntity<MessageResponse<Object>> responseEntity = userController.createUser(user, req);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
	}


	@Test
	public void personalizationSettingsSuccess() throws Exception {
		result = userController.createPersonalizationSettings(request);
		assertNotNull(result);
		assertNotNull(result.getBody());
		assertEquals(result.getStatusCode(), HttpStatus.CREATED);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void accountStatusUserIdEmpty() throws Exception {
		Mockito.when(validationUtils.stringRequestParamValidation(Matchers.anyString())).thenThrow(ResponseStatusException.class);
		Object responseObject = userController.accountStatusAndSettings("");
	}
	
	@Test
	public void accountStatusUserIdValid() throws Exception {
		//output = new Company();
		//output.setCompanyId(3000005);
		Mockito.when(validationUtils.stringRequestParamValidation(Matchers.anyString())).thenReturn(true);
		Mockito.when(accountSettings.accountSettings(Matchers.anyString())).thenReturn(jsonObject);
		Object responseObject = (JSONObject) userController.accountStatusAndSettings("MPOND");
		assertNotNull(responseObject);
		assertEquals(responseObject.toString(), jsonObject.toString());
	}
	
	@Test(expected = ResponseStatusException.class)
	public void pendingUserRequestsUserIdEmpty() throws Exception {
		Mockito.when(validationUtils.stringRequestParamValidation(Matchers.anyString())).thenThrow(ResponseStatusException.class);
		Object responseObject = userController.pendingUserApprovals("");
	}
	
	@Test
	public void pendingUserRequests() throws Exception {
		Mockito.when(validationUtils.stringRequestParamValidation(Matchers.anyString())).thenReturn(true);
		Mockito.when(pendingApprovals.pendingUserApprovals(Matchers.anyString())).thenReturn(jsonObject);
		JSONObject responseObject = userController.pendingUserApprovals("lmonendo");
		assertNotNull(responseObject);
		assertEquals(responseObject.toString(), jsonObject.toString());
	}


}
