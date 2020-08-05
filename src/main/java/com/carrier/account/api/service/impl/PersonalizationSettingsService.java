package com.carrier.account.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import com.carrier.account.api.jpa.entity.CompanyRolesPersonalization;
import com.carrier.account.api.jpa.entity.CompanyTypesPersonalization;
import com.carrier.account.api.jpa.entity.UserCompanyRolesPersonalization;
import com.carrier.account.api.jpa.entity.UserCompanyTypesPersonalization;
import com.carrier.account.api.jpa.repository.UserCompanyRolesPersonalizationRepository;
import com.carrier.account.api.jpa.repository.UserCompanyTypesPersonalizationRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Service to validate personalization settings request and save entity.
 *
 */
@Service(value = "settings")
@Slf4j
public class PersonalizationSettingsService {

	/**
	 * Initialization of userCompanyTypesPersonalizationRepository.
	 */
	@Autowired
	private UserCompanyTypesPersonalizationRepository userCompanyTypesPersonalizationRepository;
	/**
	 * Initialization of userCompanyRolesPersonalizationRepository.
	 */
	@Autowired
	private UserCompanyRolesPersonalizationRepository userCompanyRolesPersonalizationRepository;
	/**
	 * Initialization of CompanyTypesServiceimpl.
	 */
	@Autowired
	@Qualifier("companyTypes")
	private CompanyTypesServiceimpl companyTypesService;
	/**
	 * Initialization of CompanyRolesServiceImpl.
	 */
	@Autowired
	@Qualifier("companyRoles")
	private CompanyRolesServiceImpl companyRolesService;

	/**
	 * Method to save personalization settings.
	 * @param types companyTyoes as list
	 * @param roles CompanyRoles as list
	 * @param userId userid
	 * @return response message
	 */
	public boolean createPersonalizationSettings(List<String> types, List<String> roles,
			String userId) {
		boolean personalization;
		if (StringUtils.isEmpty(userId) || userId.length() == 0) {
			log.error("*****userid empty****");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "userID not empty");
		} else {
			try {
				if (types.size() > 0) {
					List<CompanyTypesPersonalization> companyTypes = companyTypesService.findByCompanyTypeIn(types);
					for (CompanyTypesPersonalization companyType : companyTypes) {
						UserCompanyTypesPersonalization userCompanyTypes = userCompanyTypesPersonalizationRepository
								.findUserByUserIdAndCompanyTypesId(userId, companyType.getId());
						if (userCompanyTypes != null) {
							userCompanyTypes.setCompanyTypesId(companyType.getId());
							userCompanyTypes.setUserId(userId);
							userCompanyTypesPersonalizationRepository.save(userCompanyTypes);
						} else {
							UserCompanyTypesPersonalization userCompanyTypesPersonalization = new UserCompanyTypesPersonalization(
									companyType.getId(), userId);
							UserCompanyTypesPersonalization response = userCompanyTypesPersonalizationRepository
									.save(userCompanyTypesPersonalization);
						}
					}
				}
				if (roles.size() > 0) {
					List<CompanyRolesPersonalization> companyRoles = companyRolesService.findByCompanyRoleIn(roles);
					for (CompanyRolesPersonalization companyRole : companyRoles) {
						UserCompanyRolesPersonalization userCompanyRoles = userCompanyRolesPersonalizationRepository
								.findUserByUserIdAndCompanyRolesId(userId, companyRole.getId());
						if (userCompanyRoles != null) {
							userCompanyRoles.setCompanyRolesId(companyRole.getId());
							userCompanyRoles.setUserId(userId);
							userCompanyRolesPersonalizationRepository.save(userCompanyRoles);
						} else {
							UserCompanyRolesPersonalization userCompanyRolesPersonalization = new UserCompanyRolesPersonalization(
									companyRole.getId(), userId);

							UserCompanyRolesPersonalization response = userCompanyRolesPersonalizationRepository
									.save(userCompanyRolesPersonalization);
						}
					}
				}
				personalization = true;
			} catch (Exception ex) {
				personalization = false;
				log.error("personalization settings exception : " + ex.getMessage());
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
						"failed to save personalization settings");
			}
		}
		return personalization;
	}

}
