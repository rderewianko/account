package com.carrier.account.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.carrier.account.api.jpa.entity.UserCompanyRolesPersonalization;
import com.carrier.account.api.jpa.repository.UserCompanyRolesPersonalizationRepository;

/**
 * Service to implement UserCompanyRolesPersonalizationRepository methods.
 *
 */
@Service(value = "userCompanyRoles")
public class UserCompanyRolesPersonalizationServiceImpl implements UserCompanyRolesPersonalizationRepository {

	/**
	 * Initialization of CompanyTypesPersonalizationRepository.
	 */
	@Autowired
	private UserCompanyRolesPersonalizationRepository userCompanyRolesRepository;

	/**
	 * method to findAll UserCompanyRolesPersonalization records.
	 */
	@Override
	public List<UserCompanyRolesPersonalization> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * method to findall UserCompanyRolesPersonalization based on sort mechanism.
	 */
	@Override
	public List<UserCompanyRolesPersonalization> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * find by Id.
	 */
	@Override
	public List<UserCompanyRolesPersonalization> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * save all entities.
	 */
	@Override
	public <S extends UserCompanyRolesPersonalization> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * flush method to save entity to DB.
	 */
	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	/**
	 * save and flush entity.
	 */
	@Override
	public <S extends UserCompanyRolesPersonalization> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * delete record in batch.
	 */
	@Override
	public void deleteInBatch(Iterable<UserCompanyRolesPersonalization> entities) {
		// TODO Auto-generated method stub

	}

	/**
	 * delete all in batch.
	 */
	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub

	}

	/**
	 * get one record by id.
	 */
	@Override
	public UserCompanyRolesPersonalization getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * find all.
	 */
	@Override
	public <S extends UserCompanyRolesPersonalization> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * find all records .
	 */
	@Override
	public <S extends UserCompanyRolesPersonalization> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * find all with pageable.
	 */
	@Override
	public Page<UserCompanyRolesPersonalization> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * save entity.
	 */
	@Override
	public <S extends UserCompanyRolesPersonalization> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * find by id.
	 */
	@Override
	public Optional<UserCompanyRolesPersonalization> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * exists by Id.
	 */
	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * count of records.
	 */
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * delete by ID.
	 */
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	/**
	 * delete entity.
	 */
	@Override
	public void delete(UserCompanyRolesPersonalization entity) {
		// TODO Auto-generated method stub

	}

	/**
	 * delete all entities.
	 */
	@Override
	public void deleteAll(Iterable<? extends UserCompanyRolesPersonalization> entities) {
		// TODO Auto-generated method stub

	}

	/**
	 * delete all.
	 */
	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	/**
	 * find one record.
	 */
	@Override
	public <S extends UserCompanyRolesPersonalization> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * find all with pageable.
	 */
	@Override
	public <S extends UserCompanyRolesPersonalization> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * count of records.
	 */
	@Override
	public <S extends UserCompanyRolesPersonalization> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * user record exists or not.
	 */
	@Override
	public <S extends UserCompanyRolesPersonalization> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * find by userId and companyRolesId.
	 */
	@Override
	public UserCompanyRolesPersonalization findUserByUserIdAndCompanyRolesId(String userId, int companyRolesId) {
		// TODO Auto-generated method stub
		return userCompanyRolesRepository.findUserByUserIdAndCompanyRolesId(userId, companyRolesId);
	}

}
