/*
 * package com.carrier.hvac.account.api.controller.test;
 * 
 * import static org.junit.jupiter.api.Assertions.assertEquals; import static
 * org.junit.jupiter.api.Assertions.assertNotNull; import static
 * org.junit.jupiter.api.Assertions.assertTrue;
 * 
 * import org.junit.Test; import org.junit.jupiter.api.BeforeEach; import
 * org.junit.runner.RunWith; import org.mockito.InjectMocks; import
 * org.mockito.Matchers; import org.mockito.Mock; import org.mockito.Mockito;
 * import org.mockito.junit.MockitoJUnitRunner; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.web.server.ResponseStatusException;
 * 
 * import com.carrier.account.api.controller.PersonalizationController; import
 * com.carrier.account.api.jpa.entity.UserPersonalization; import
 * com.carrier.account.api.service.impl.PersonalizationSettingsService;
 * 
 * @RunWith(MockitoJUnitRunner.class) public class PersonalizationControllerTest
 * {
 * 
 * @InjectMocks private PersonalizationController PersonalizationController;
 * 
 * @Mock private PersonalizationSettingsService personalizationSettingsService;
 * 
 * @Mock private UserPersonalization userPersonalization = new
 * UserPersonalization();
 * 
 * @Mock UserPersonalization personalizationResponse = new
 * UserPersonalization();
 * 
 * @Mock private ResponseStatusException responseStatusException;
 * 
 * @BeforeEach public void setup() {
 * 
 * }
 * 
 * 
 * @Test(expected = ResponseStatusException.class) public void
 * personalizationSettingsUserIdValidation() throws Exception {
 * Mockito.when(personalizationSettingsService.createPersonalizationSettings(
 * Matchers.anyObject())) .thenThrow(ResponseStatusException.class);
 * userPersonalization.setUserId(0);
 * PersonalizationController.createPersonalizationSettings(userPersonalization);
 * }
 * 
 * 
 * @Test public void personalizationSettingsSave() throws Exception {
 * userPersonalization = new UserPersonalization();
 * userPersonalization.setUserId(10);
 * userPersonalization.setCompanyRole("Service-test"); personalizationResponse =
 * new UserPersonalization(); personalizationResponse.setUserId(10);
 * personalizationResponse.setCompanyRole("Service-test");
 * responseStatusException = new ResponseStatusException(HttpStatus.OK,
 * "Personalization settings created successfully");
 * Mockito.when(personalizationSettingsService.createPersonalizationSettings(
 * userPersonalization)).thenReturn(responseStatusException);
 * ResponseStatusException response =
 * PersonalizationController.createPersonalizationSettings(
 * userPersonalization); assertNotNull(response);
 * assertTrue(response.getMessage().
 * contains("Personalization settings created successfully"));
 * assertEquals(response.getStatus(), HttpStatus.OK); }
 * 
 * }
 */