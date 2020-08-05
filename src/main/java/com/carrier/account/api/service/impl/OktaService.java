package com.carrier.account.api.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.carrier.account.api.jpa.entity.User;

import lombok.extern.slf4j.Slf4j;

/**
 * service class to validate username and create user.
 *
 */
@Service(value = "oktaService")
@Slf4j
public class OktaService {

	/**
	 * Initialization of RestTemplate.
	 */
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Initialization of oktaEndPoint for unsername validation.
	 */
	@Value("${okta.username.EndPoint}")
	private String oktaEndPoint;

	/**
	 * Initialization of oktaAuthString.
	 */
	@Value("${okta.auth.token}")
	private String oktaAuthString;

	/**
	 * Initialization of oktaUserCreate endpoint.
	 */
	@Value("${okta.user.create}")
	private String oktaUserCreate;

	/**
	 * Initialization of RestTemplate object.
	 *
	 * @param builder builder
	 * @return restTemlape
	 */
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	/**
	 * Validation of username(isavailable or not) in Okta domain.
	 *
	 * @param name userName
	 * @return true/false
	 * @throws ResourceConflictException ResourceConflictException
	 */
	public boolean isUserameAvailable(String name) {
		boolean userNameAvailable = false;
		final String uri = oktaEndPoint + name;

		// create headers
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", oktaAuthString);

		// create request
		HttpEntity request = new HttpEntity(headers);
		try {
			ResponseEntity<String> response = new RestTemplate().exchange(uri, HttpMethod.GET, request, String.class);

			int responseCode = response.getStatusCodeValue();
			log.info("Okta username validation status code" + responseCode);
			if (responseCode == 200 || responseCode == 404) {
				// get JSON response
				String json = response.getBody();
				if (json.contains("profile") && json.contains("status")) {
					log.error("Okta username validation - username already exists");
					userNameAvailable = false;
				} else {
					log.info("Okta username available.......");
					userNameAvailable = true;
				}
			} else {
				if (responseCode == 403) {
					log.error("Okta username validation - access denied");
					throw new ResponseStatusException(HttpStatus.FORBIDDEN, "access denied");
				} else {
					log.error("Okta username validation - internal server error");
					throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
				}
			}
		} catch (Exception ex) {
			String message = ex.getMessage();
			String[] responseDetails = message.split(": ", 2);
			log.error("Okta username validation - error message :" + message);
			if (responseDetails.length > 1) {
				if (responseDetails[0].equalsIgnoreCase("404 Not Found")) {
					userNameAvailable = true;
				} else {
					log.error("Okta username validation - internal server error :" + message);
					throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, message);
				}
			}
		}
		return userNameAvailable;
	}

	/**
	 * Create user in okta domain.
	 *
	 * @param userInfo userInfo
	 * @return true.false
	 */
	public boolean createOktaUser(User userInfo) {
		final String uri = oktaUserCreate;
		// create headers
		HttpHeaders headers = new HttpHeaders();
		// set `content-type` header
		headers.setContentType(MediaType.APPLICATION_JSON);
		// set `accept` header
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", oktaAuthString);
		JSONObject inputRequest = new JSONObject();
		// request body parameters
		Map<String, Object> profileMap = new HashMap<>();
		profileMap.put("firstName", userInfo.getFirstName());
		profileMap.put("lastName", userInfo.getLastName());
		profileMap.put("email", userInfo.getEmailAddress());
		profileMap.put("login", userInfo.getUsername());
		profileMap.put("mobilePhone", userInfo.getPhoneNumber());

		inputRequest.put("profile", profileMap);

		Map<String, Object> crenditialMap = new HashMap<>();
		crenditialMap.put("value", userInfo.getPassword());
		JSONObject passwordObject = new JSONObject();
		passwordObject.put("password", crenditialMap);
		inputRequest.put("credentials", passwordObject);

		// build the request
		HttpEntity request = new HttpEntity<>(inputRequest, headers);

		try {
			// send POST request
			ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				log.info("Okta create user statuscode" + response.getStatusCode());
				log.info("Okta craete user response body" + response.getBody());
				return true;
			}
		} catch (Exception ex) {
			String message = ex.getMessage();
			String[] responseDetails = message.split(": ", 2);
			if (responseDetails.length > 1) {
				if (responseDetails[0].equalsIgnoreCase("400 Bad Request")) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
							responseDetails[1].substring(1, responseDetails[1].length() - 1));
				} else if (responseDetails[0].equalsIgnoreCase("401 Unauthorized")) {
					throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
							responseDetails[1].substring(1, responseDetails[1].length() - 1));
				} else if (responseDetails[0].equalsIgnoreCase("403 Forbidden")) {
					throw new ResponseStatusException(HttpStatus.FORBIDDEN,
							responseDetails[1].substring(1, responseDetails[1].length() - 1));
				} else {
					throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, message);
				}
			}
		}

		return false;
	}

}
