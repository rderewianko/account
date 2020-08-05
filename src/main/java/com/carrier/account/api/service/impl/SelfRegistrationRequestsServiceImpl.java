package com.carrier.account.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.carrier.account.api.jpa.entity.SelfRegistrationRequests;
import com.carrier.account.api.jpa.repository.SelfRegistrationRequestsRepository;

/**
 * SelfRegistrationRequestsServiceImpl class to process repository methods.
 */
@Service(value = "selfRegrequest")
public class SelfRegistrationRequestsServiceImpl implements SelfRegistrationRequestsRepository {

	/**
	 * Initialization of CompanyTypesPersonalizationRepository.
	 */
	@Autowired
	private SelfRegistrationRequestsRepository selfRegRepository;

	/**
	 * findAll method to return entities.
	 */
	@Override
	public List<com.carrier.account.api.jpa.entity.SelfRegistrationRequests> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * find all method to return all entities with sort mechanism.
	 */
	@Override
	public List<com.carrier.account.api.jpa.entity.SelfRegistrationRequests> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * find all by Id.
	 */
	@Override
	public List<com.carrier.account.api.jpa.entity.SelfRegistrationRequests> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * save all entities.
	 */
	@Override
	public <S extends com.carrier.account.api.jpa.entity.SelfRegistrationRequests> List<S> saveAll(
			Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * flush method to save to DB.
	 */
	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	/**
	 * save and flush entity.
	 */
	@Override
	public <S extends com.carrier.account.api.jpa.entity.SelfRegistrationRequests> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * delete entity in batch.
	 */
	@Override
	public void deleteInBatch(Iterable<com.carrier.account.api.jpa.entity.SelfRegistrationRequests> entities) {
		// TODO Auto-generated method stub

	}

	/**
	 * delete all entities in batch.
	 */
	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub

	}

	/**
	 * get one entity by Id.
	 */
	@Override
	public com.carrier.account.api.jpa.entity.SelfRegistrationRequests getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * find all entities.
	 */
	@Override
	public <S extends com.carrier.account.api.jpa.entity.SelfRegistrationRequests> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * find all with sort.
	 */
	@Override
	public <S extends com.carrier.account.api.jpa.entity.SelfRegistrationRequests> List<S> findAll(Example<S> example,
			Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * find all with pageable.
	 */
	@Override
	public Page<com.carrier.account.api.jpa.entity.SelfRegistrationRequests> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * save entity.
	 */
	@Override
	public <S extends com.carrier.account.api.jpa.entity.SelfRegistrationRequests> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * find by id.
	 */
	@Override
	public Optional<com.carrier.account.api.jpa.entity.SelfRegistrationRequests> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * check entity exists by id or not.
	 */
	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * count of entities.
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
	public void delete(com.carrier.account.api.jpa.entity.SelfRegistrationRequests entity) {
		// TODO Auto-generated method stub

	}

	/**
	 * delete all entities.
	 */
	@Override
	public void deleteAll(Iterable<? extends com.carrier.account.api.jpa.entity.SelfRegistrationRequests> entities) {
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
	 * find one entity.
	 */
	@Override
	public <S extends com.carrier.account.api.jpa.entity.SelfRegistrationRequests> Optional<S> findOne(
			Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * findAll method.
	 */
	@Override
	public <S extends com.carrier.account.api.jpa.entity.SelfRegistrationRequests> Page<S> findAll(Example<S> example,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * count of entities.
	 */
	@Override
	public <S extends com.carrier.account.api.jpa.entity.SelfRegistrationRequests> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * boolean check of entity.
	 */
	@Override
	public <S extends com.carrier.account.api.jpa.entity.SelfRegistrationRequests> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * find entity by userName.
	 */
	@Override
	public SelfRegistrationRequests findByUserName(String userid) {
		// TODO Auto-generated method stub
		return selfRegRepository.findByUserName(userid);
	}

}
