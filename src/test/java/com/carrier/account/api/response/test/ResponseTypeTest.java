package com.carrier.account.api.response.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

import com.carrier.account.api.response.ResponseType;

@RunWith(MockitoJUnitRunner.class)
public class ResponseTypeTest {
	
	@InjectMocks
	private ResponseType responseType;
	
	@Mock
	JSONObject mockObject;
	
	@BeforeEach
	public void setUp() {
	}
	
	@Test
	public void successResponseTest() {
		JSONObject responseObject = responseType.successResponse(HttpStatus.OK,"Success Message","/");
		assertNotNull(responseObject);
		assertEquals(responseObject.get("message"), "Success Message");
		assertEquals(responseObject.get("status"), "200");
		assertEquals(responseObject.get("error"), "OK");
		assertEquals(responseObject.get("path"), "/");
	}

}
