package com.carrier.account.api.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrier.account.api.jpa.entity.CompanyTypesPersonalization;

/**
 * CompanyTypesPersonalization JPA repository interface.
 *
 */
@Repository
public interface CompanyTypesPersonalizationRepository extends JpaRepository<CompanyTypesPersonalization, Long>  {

	/**
	 * Method to return CompanyTypes personalization records using IN clause.
	 * @param types types
	 * @return List of CompanyTypesPersonalization records.
	 */
	List<CompanyTypesPersonalization> findByCompanyTypeIn(List<String> types);
}
