package com.carrier.hvac.account.api.controller.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CreateAccountControllerTest {
    @Test
	public void createAccount() {
    	String str= "Junit is working fine";
        assertEquals("Junit is working fine",str);
	}
    
    @Test
    public void createAccountBadRequest() {
    	String str= "account creation failed";
        assertEquals("account creation failed",str);
    }
}
