package com.carrier.account.api.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrier.account.api.jpa.entity.UserCompanyTypesPersonalization;

/**
 * Repository of UserCompanyTypesPersonalization.
 *
 */
@Repository
public interface UserCompanyTypesPersonalizationRepository
		extends JpaRepository<UserCompanyTypesPersonalization, Long> {
	/**
	 * Gets UserCompanyTypesPersonalization details from the DB.
	 * @param userId userId
	 * @param companyTypesId companyTypesId
	 * @return UserCompanyTypesPersonalizationInstance
	 */
	UserCompanyTypesPersonalization findUserByUserIdAndCompanyTypesId(String userId, int companyTypesId);

}
