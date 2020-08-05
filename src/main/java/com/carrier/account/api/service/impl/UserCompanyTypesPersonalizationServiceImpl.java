package com.carrier.account.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.carrier.account.api.jpa.entity.UserCompanyTypesPersonalization;
import com.carrier.account.api.jpa.repository.UserCompanyTypesPersonalizationRepository;

/**
 * Service to implement UserCompanyTypesPersonalizationRepository methods.
 *
 */
@Service(value = "userCompanyTypes")
public class UserCompanyTypesPersonalizationServiceImpl implements UserCompanyTypesPersonalizationRepository {
	/**
	 * Initialization of CompanyTypesPersonalizationRepository.
	 */
	@Autowired
	private UserCompanyTypesPersonalizationRepository userCompanyTypesRepository;

	/**
	 * method to find all UserCompanyTypesPersonalization records from DB.
	 */
	@Override
	public List<UserCompanyTypesPersonalization> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * method to find all UserCompanyTypesPersonalization records based on sort
	 * mechanism.
	 */
	@Override
	public List<UserCompanyTypesPersonalization> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * method to find all UserCompanyTypesPersonalization records based on ID.
	 */
	@Override
	public List<UserCompanyTypesPersonalization> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * saveAll entities.
	 */
	@Override
	public <S extends UserCompanyTypesPersonalization> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * flush record to DB.
	 */
	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	/**
	 * save and flush record.
	 */
	@Override
	public <S extends UserCompanyTypesPersonalization> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * delete batch of entities.
	 */
	@Override
	public void deleteInBatch(Iterable<UserCompanyTypesPersonalization> entities) {
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
	 * get one record based on id.
	 */
	@Override
	public UserCompanyTypesPersonalization getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * find all method.
	 */
	@Override
	public <S extends UserCompanyTypesPersonalization> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * find all method.
	 */
	@Override
	public <S extends UserCompanyTypesPersonalization> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * find all with pageable.
	 */
	@Override
	public Page<UserCompanyTypesPersonalization> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * save entity.
	 */
	@Override
	public <S extends UserCompanyTypesPersonalization> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * find by Id.
	 */
	@Override
	public Optional<UserCompanyTypesPersonalization> findById(Long id) {
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
	public void delete(UserCompanyTypesPersonalization entity) {
		// TODO Auto-generated method stub

	}

	/**
	 * dlete all entities.
	 */
	@Override
	public void deleteAll(Iterable<? extends UserCompanyTypesPersonalization> entities) {
		// TODO Auto-generated method stub

	}

	/**
	 * delete all method.
	 */
	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	/**
	 * find one.
	 */
	@Override
	public <S extends UserCompanyTypesPersonalization> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * find all.
	 */
	@Override
	public <S extends UserCompanyTypesPersonalization> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * count of records.
	 */
	@Override
	public <S extends UserCompanyTypesPersonalization> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * exists of record.
	 */
	@Override
	public <S extends UserCompanyTypesPersonalization> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * find record based on userId and companyTypesId.
	 */
	@Override
	public UserCompanyTypesPersonalization findUserByUserIdAndCompanyTypesId(String userId, int companyTypesId) {
		// TODO Auto-generated method stub
		return userCompanyTypesRepository.findUserByUserIdAndCompanyTypesId(userId, companyTypesId);
	}

}
