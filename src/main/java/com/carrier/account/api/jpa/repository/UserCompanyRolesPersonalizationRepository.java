package com.carrier.account.api.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrier.account.api.jpa.entity.UserCompanyRolesPersonalization;

/**
 * repository of UserCompanyRolesPersonalization.
 *
 */
@Repository
public interface UserCompanyRolesPersonalizationRepository
		extends JpaRepository<UserCompanyRolesPersonalization, Long> {
	/**
	 * Gets UserCompanyRolesPersonalization details from the DB.
	 * @param userId userId
	 * @param companyRolesId companyRolesId
	 * @return UserCompanyRolesPersonalization
	 */
	UserCompanyRolesPersonalization findUserByUserIdAndCompanyRolesId(String userId, int companyRolesId);
}
