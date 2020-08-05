package com.carrier.account.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.carrier.account.api.jpa.entity.CompanyTypesPersonalization;
import com.carrier.account.api.jpa.repository.CompanyTypesPersonalizationRepository;

/**
 * Service to implement CompanyTypesPersonalizationRepository methods.
 *
 */
@Service(value = "companyTypes")
public class CompanyTypesServiceimpl implements CompanyTypesPersonalizationRepository {

	/**
	 * Initialization of CompanyTypesPersonalizationRepository.
	 */
	@Autowired
	private CompanyTypesPersonalizationRepository companyTypesRepository;

	/**
	 * find all entities.
	 */
	@Override
	public List<CompanyTypesPersonalization> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * findAll entities with sort.
	 */
	@Override
	public List<CompanyTypesPersonalization> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * find all entities by list of Ids.
	 */
	@Override
	public List<CompanyTypesPersonalization> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * save all entities.
	 */
	@Override
	public <S extends CompanyTypesPersonalization> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * flush entity.
	 */
	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	/**
	 * save and flush entity.
	 */
	@Override
	public <S extends CompanyTypesPersonalization> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * delete entities.
	 */
	@Override
	public void deleteInBatch(Iterable<CompanyTypesPersonalization> entities) {
		// TODO Auto-generated method stub

	}

	/**
	 * delete all entities.
	 */
	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub

	}

	/**
	 * get one entity by id.
	 */
	@Override
	public CompanyTypesPersonalization getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * find all entities.
	 */
	@Override
	public <S extends CompanyTypesPersonalization> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * find all entities - with sort.
	 */
	@Override
	public <S extends CompanyTypesPersonalization> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * find all entities - with pagination.
	 */
	@Override
	public Page<CompanyTypesPersonalization> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * save entity.
	 */
	@Override
	public <S extends CompanyTypesPersonalization> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * find by Id.
	 */
	@Override
	public Optional<CompanyTypesPersonalization> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * entity check by ID.
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
	 * delete by Id.
	 */
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	/**
	 * delete entity.
	 */
	@Override
	public void delete(CompanyTypesPersonalization entity) {
		// TODO Auto-generated method stub

	}

	/**
	 * delete all entities.
	 */
	@Override
	public void deleteAll(Iterable<? extends CompanyTypesPersonalization> entities) {
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
	 * find one entity.
	 */
	@Override
	public <S extends CompanyTypesPersonalization> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * find all with pagable.
	 */
	@Override
	public <S extends CompanyTypesPersonalization> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * count of entities.
	 */
	@Override
	public <S extends CompanyTypesPersonalization> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * entity check.
	 */
	@Override
	public <S extends CompanyTypesPersonalization> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * find list of entities by companyType in list of types.
	 */
	@Override
	public List<CompanyTypesPersonalization> findByCompanyTypeIn(List<String> types) {
		return companyTypesRepository.findByCompanyTypeIn(types);
	}

}
