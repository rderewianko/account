package com.carrier.account.api.controller;

import static com.carrier.account.api.constants.AccountConstants.EMAIL_ALREADY_EXISTS;
import static com.carrier.account.api.constants.AccountConstants.EMAIL_AVAILABLE;
import static com.carrier.account.api.constants.AccountConstants.PERSONALIZATION_SETTINGS_SUCCESS;
import static com.carrier.account.api.constants.AccountConstants.USER_NAME_ALREADY_EXISTS;
import static com.carrier.account.api.constants.AccountConstants.USER_NAME_AVAILABLE;
import static com.carrier.account.api.constants.AccountConstants.USER_SUCCESS_MESSAGE;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.carrier.account.api.exception.ResourceConflictException;
import com.carrier.account.api.jpa.entity.PersonalizationRequest;
import com.carrier.account.api.jpa.entity.User;
import com.carrier.account.api.response.MessageResponse;
import com.carrier.account.api.service.ICommonService;
import com.carrier.account.api.service.impl.AccountSettingsService;
import com.carrier.account.api.service.impl.OktaService;
import com.carrier.account.api.service.impl.PendingApprovalsService;
import com.carrier.account.api.service.impl.PersonalizationSettingsService;
import com.carrier.account.api.utils.AccountApiUtils;
import com.carrier.account.api.validation.ValidationUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller to process Username lookup request.
 *
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

	/**
	 * Initialization of userService.
	 */
	@Autowired
	@Qualifier("user")
	private ICommonService<User, String> userService;
	/**
	 * Initialization of personalization Service.
	 */
	@Autowired
	@Qualifier("settings")
	private PersonalizationSettingsService personalizationService;
	/**
	 * Initialization of validationUtils.
	 */
	@Autowired
	@Qualifier("validation")
	private ValidationUtils validationUtils;
	/**
	 * Initialization of AccountSettingsService.
	 */
	@Autowired
	@Qualifier("accountSettings")
	private AccountSettingsService accountSettings;
	/**
	 * Initialization of PendingApprovalsService.
	 */

	@Autowired
	@Qualifier("userRequests")
	private PendingApprovalsService pendingApprovals;
	/**
	 * Initialization of okta service.
	 */
	@Autowired
	@Qualifier("oktaService")
	private OktaService oktaService;

	/**
	 * Method to process Username lookup by username.
	 *
	 * @param name "abc"
	 * @param req  requestObj
	 * @return ResponseEntity
	 * @throws ResourceConflictException ResourceConflictException
	 */
	@GetMapping(path = "/user/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<MessageResponse<Object>> getUserById(@PathVariable(value = "username") String name,
			HttpServletRequest req) throws ResourceConflictException {
		log.info("Requested username for validation: {}", name);
		if (oktaService.isUserameAvailable(name)) {
			boolean isExists = userService.isExists(name);
			log.info("Username exists or not :" + isExists);
			if (isExists) {
				throw new ResourceConflictException(USER_NAME_ALREADY_EXISTS + name);
			}
		} else {
			throw new ResourceConflictException(USER_NAME_ALREADY_EXISTS + name);
		}
		return AccountApiUtils.buildResponse("Success", HttpStatus.OK.toString(), USER_NAME_AVAILABLE);
	}

	/**
	 * Creates a new user.
	 *
	 * @param user    userDetails
	 * @param request requestObj
	 * @return ResponseEntity
	 */
	@PostMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<MessageResponse<Object>> createUser(@Valid @RequestBody User user,
			HttpServletRequest request) {
		oktaService.createOktaUser(user);
		userService.createEntity(user);
		log.info("Successfully created User Record In Database");
		return new ResponseEntity<>(AccountApiUtils.postMethodResponse("Success", HttpStatus.CREATED.toString(), USER_SUCCESS_MESSAGE), HttpStatus.CREATED);
	}

	/**
	 * method to create new PersonalizationSettings.
	 * @param request PersonalizationRequest object
	 * @return responseEntity object
	 */
	@PostMapping(path = "/user/settings", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> createPersonalizationSettings(@RequestBody PersonalizationRequest request) {
		personalizationService.createPersonalizationSettings(request.getCompanyTypes(), request.getCompanyRoles(), request.getUserId());
		log.info("Successfully created Personalization settings record In Database");
		return new ResponseEntity<>(AccountApiUtils.postMethodResponse("Success", HttpStatus.CREATED.toString(), PERSONALIZATION_SETTINGS_SUCCESS), HttpStatus.CREATED);
	}
	/**
	 * Method to fetch account status and personalization settings.
	 * @param userId userId
	 * @return list of settings and account status
	 * @throws SQLException SQLException
	 */
	@GetMapping(path = "user/status", params = "userId", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object accountStatusAndSettings(@RequestParam(value = "userId") String userId)
			throws SQLException {
		if (validationUtils.stringRequestParamValidation(userId)) {
			return accountSettings.accountSettings(userId);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "userId not empty");
		}
	}
	/**
	 * method to fetch pending approvals for userId, if userId as administrator.
	 * @param userId userId
	 * @return list of approval pending users
	 */
	@GetMapping(path = "user/userApprovalRequests", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JSONObject pendingUserApprovals(@RequestParam(value = "userId") String userId) {
		if (validationUtils.stringRequestParamValidation(userId)) {
			return pendingApprovals.pendingUserApprovals(userId);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "userId not empty");
		}

	}
    /**
     * Method that validates email in blackjack DB.
     * @param email "test@gmail.com"
     * @param req req
     * @return ResponseEntity
     * @throws ResourceConflictException "Email Already Exists"
     */
	@GetMapping(path = "/user/mail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<MessageResponse<Object>> isEmailAlreadyExists(@PathVariable(value = "email") String email,
			HttpServletRequest req) throws ResourceConflictException {
		log.info("Requested username for validation: {}", email);
		boolean isExists = userService.isEmailExists(email);
		log.info("Username exists or not :" + isExists);
		if (isExists) {
			throw new ResourceConflictException(EMAIL_ALREADY_EXISTS + email);
		}
		return AccountApiUtils.buildResponse("Success", HttpStatus.OK.toString(), EMAIL_AVAILABLE);
	}


}
