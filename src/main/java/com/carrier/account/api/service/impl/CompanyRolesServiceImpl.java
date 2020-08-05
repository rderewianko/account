package com.carrier.account.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.carrier.account.api.jpa.entity.CompanyRolesPersonalization;
import com.carrier.account.api.jpa.repository.CompanyRolesPersonalizationRepository;

/**
 * Service to implement CompanyRolesPersonalizationRepository methods.
 *
 */
@Service(value = "companyRoles")
public class CompanyRolesServiceImpl implements CompanyRolesPersonalizationRepository {
	/**
	 * Initialization of CompanyRolesPersonalizationRepository.
	 */
	@Autowired
	private CompanyRolesPersonalizationRepository companyRolesRepository;

	/**
	 * Method to find all CompanyRolesPersonalization records.
	 */
	@Override
	public List<CompanyRolesPersonalization> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method to find all CompanyRolesPersonalization records using sort parameter.
	 */
	@Override
	public List<CompanyRolesPersonalization> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method to findAllById CompanyRolesPersonalization records.
	 */
	@Override
	public List<CompanyRolesPersonalization> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * method to save all entities.
	 */
	@Override
	public <S extends CompanyRolesPersonalization> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * method flush records.
	 */
	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	/**
	 * save and flush entities to DB.
	 */
	@Override
	public <S extends CompanyRolesPersonalization> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * delete entities using batch.
	 */
	@Override
	public void deleteInBatch(Iterable<CompanyRolesPersonalization> entities) {
		// TODO Auto-generated method stub

	}

	/**
	 * delete all.
	 */
	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub

	}

	/**
	 * save entity.
	 */
	@Override
	public <S extends CompanyRolesPersonalization> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * return entity findByID.
	 */
	@Override
	public Optional<CompanyRolesPersonalization> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * check entity by id.
	 */
	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * find count.
	 */
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * delete by id.
	 */
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	/**
	 * delete entity.
	 */
	@Override
	public void delete(CompanyRolesPersonalization entity) {
		// TODO Auto-generated method stub

	}

	/**
	 * delete all entities.
	 */
	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	/**
	 * entity exists or not.
	 */
	@Override
	public <S extends CompanyRolesPersonalization> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * return entities findBy companyRoleIn - roles as list.
	 */
	@Override
	public List<CompanyRolesPersonalization> findByCompanyRoleIn(List<String> roles) {
		// TODO Auto-generated method stub
		return companyRolesRepository.findByCompanyRoleIn(roles);
	}

	/**
	 * find single record.
	 */
	@Override
	public CompanyRolesPersonalization getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * find list of all records.
	 */
	@Override
	public <S extends CompanyRolesPersonalization> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * find all records and sort.
	 */
	@Override
	public <S extends CompanyRolesPersonalization> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * findAll records - with pagination.
	 */
	@Override
	public Page<CompanyRolesPersonalization> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * delete all entities.
	 */
	@Override
	public void deleteAll(Iterable<? extends CompanyRolesPersonalization> entities) {
		// TODO Auto-generated method stub
	}

	/**
	 * find single entity.
	 */
	@Override
	public <S extends CompanyRolesPersonalization> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * find all entities.
	 */
	@Override
	public <S extends CompanyRolesPersonalization> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * return count of entities.
	 */
	@Override
	public <S extends CompanyRolesPersonalization> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

}
