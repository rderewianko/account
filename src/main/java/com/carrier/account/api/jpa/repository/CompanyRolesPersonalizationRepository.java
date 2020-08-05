package com.carrier.account.api.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrier.account.api.jpa.entity.CompanyRolesPersonalization;

/**
 * repository of CompanyRolesPersonalization.
 *
 */
@Repository
public interface CompanyRolesPersonalizationRepository extends JpaRepository<CompanyRolesPersonalization, Long> {

	/**
	 * Method to return CompanyRoles personalization records using IN clause.
	 * @param roles roles
	 * @return List of CompanyRolesPersonalization records.
	 */
	List<CompanyRolesPersonalization> findByCompanyRoleIn(List<String> roles);

}
