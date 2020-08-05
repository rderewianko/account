package com.carrier.account.api.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrier.account.api.jpa.entity.SelfRegistrationRequests;
/**
 * repository of SelfRegistrationRequests.
 *
 */
@Repository
public interface SelfRegistrationRequestsRepository extends JpaRepository<SelfRegistrationRequests, Long> {

	/**
	 * find user record by userId.
	 * @param userId userId
	 * @return SelfRegistrationRequestsInstance
	 */
	SelfRegistrationRequests findByUserName(String userId);
}
