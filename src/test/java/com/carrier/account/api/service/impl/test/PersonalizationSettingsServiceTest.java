
package com.carrier.account.api.service.impl.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.carrier.account.api.jpa.entity.CompanyRolesPersonalization;
import com.carrier.account.api.jpa.entity.CompanyTypesPersonalization;
import com.carrier.account.api.jpa.entity.PersonalizationRequest;
import com.carrier.account.api.jpa.entity.UserCompanyRolesPersonalization;
import com.carrier.account.api.jpa.entity.UserCompanyTypesPersonalization;
import com.carrier.account.api.jpa.repository.UserCompanyRolesPersonalizationRepository;
import com.carrier.account.api.jpa.repository.UserCompanyTypesPersonalizationRepository;
import com.carrier.account.api.service.impl.CompanyRolesServiceImpl;
import com.carrier.account.api.service.impl.CompanyTypesServiceimpl;
import com.carrier.account.api.service.impl.PersonalizationSettingsService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PersonalizationSettingsServiceTest {

	@InjectMocks
	private PersonalizationSettingsService personalizationSettingsService;
	
	@Mock
	private UserCompanyTypesPersonalizationRepository userCompanyTypesPersonalizationRepository;
	
	@Mock
	private UserCompanyRolesPersonalizationRepository userCompanyRolesPersonalizationRepository;
	
	@Mock
	private CompanyTypesServiceimpl companyTypesService;
	
	@Mock
	private CompanyRolesServiceImpl companyRolesService;


	@Mock
	private ResponseStatusException responseStatusException;
	
	@Mock
	List<String> types;
	
	@Mock
	List<String> roles;
	
	@Mock
	List<CompanyTypesPersonalization> companyTypes;
	
	@Mock
	List<CompanyRolesPersonalization> companyRoles;
	
	@Mock
	UserCompanyTypesPersonalization userCompanyTypes;
	
	@Mock
	UserCompanyRolesPersonalization userCompanyRoles;
	
	@Mock
	PersonalizationRequest request = new PersonalizationRequest();

	@BeforeEach
	public void setUp() {
	}

	@Test(expected = ResponseStatusException.class)
	public void userIdNotExists() {
		personalizationSettingsService.createPersonalizationSettings(types, roles, "");
	}

	@Test
	public void createSettings() throws ResponseStatusException {
		types  = new ArrayList<>();
		roles = new ArrayList<>();
		Mockito.when(companyRolesService.findByCompanyRoleIn(roles)).thenReturn(companyRoles);
		Mockito.when(companyTypesService.findByCompanyTypeIn(types)).thenReturn(companyTypes);
		boolean result = personalizationSettingsService.createPersonalizationSettings(request.getCompanyTypes(), request.getCompanyRoles(), "test");
		assertNotNull(result);
		assertEquals(result, true);
	}
	
	@Test
	public void updateSettings() throws ResponseStatusException {
		types  = new ArrayList<>();
		roles = new ArrayList<>();
		Mockito.when(companyRolesService.findByCompanyRoleIn(roles)).thenReturn(companyRoles);
		Mockito.when(companyTypesService.findByCompanyTypeIn(types)).thenReturn(companyTypes);
		Mockito.when(userCompanyTypesPersonalizationRepository.findUserByUserIdAndCompanyTypesId(Matchers.anyString(), Matchers.anyInt())).thenReturn(userCompanyTypes);
		Mockito.when(userCompanyRolesPersonalizationRepository.findUserByUserIdAndCompanyRolesId(Matchers.anyString(), Matchers.anyInt())).thenReturn(userCompanyRoles);
		personalizationSettingsService.createPersonalizationSettings(request.getCompanyTypes(), request.getCompanyRoles(), "test");
		boolean result = personalizationSettingsService.createPersonalizationSettings(request.getCompanyTypes(), request.getCompanyRoles(), "test");
		assertNotNull(result);
		assertEquals(result, true);
	}

}
