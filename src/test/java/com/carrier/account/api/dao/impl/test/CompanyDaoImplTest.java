package com.carrier.account.api.dao.impl.test;

import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.carrier.account.api.dao.impl.CompanyDaoImpl;
import com.carrier.account.api.exception.ResourceNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class CompanyDaoImplTest {
	
	@InjectMocks
	private CompanyDaoImpl companyDaoImpl;
	
	@Mock
	private JSONObject jsonObject;
	
	@BeforeEach
	public void setup() {
	}
	
	@Test
	public void zipCodeNotFound() throws Exception {
		//TODO
		//Mockito.when(companyDaoImpl.getData(Matchers.anyString())).thenThrow(ResourceNotFoundException.class);
		//companyDaoImpl.getData("98052");
	}


}
