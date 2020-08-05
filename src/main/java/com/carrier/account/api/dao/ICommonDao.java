package com.carrier.account.api.dao;

import java.util.Optional;

import org.json.simple.JSONObject;

/**
 * Interface methods to process get entity.
 * @param <T> T
 * @param <E> E
 */
public interface ICommonDao<T, E> {
	/**
	 * method to process entity.
	 * @param e e
	 * @return entity
	 */
	T getData(E e);
	/**
	 * Create an entity.
	 * @param t modelobject
	 * @return int
	 */
	Optional<T> create(T t);
	/**
	 * method to process entity.
	 * @param zipcode zipcode
	 * @return entities
	 */
	JSONObject getData(String zipcode);
	/**
	 * Delete an entity.
	 * @param id id
	 * @return entity
	 */
	E delete(E id);
}
