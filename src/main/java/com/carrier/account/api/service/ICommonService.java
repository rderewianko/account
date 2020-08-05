package com.carrier.account.api.service;

import com.carrier.account.api.jpa.entity.User;

/**
 * @param <T> T
 * @param <E> E
 */
public interface ICommonService<T, E> {
	/**
	 * Find availability of record.
	 *
	 * @param str str
	 * @return boolean
	 */
	boolean isExists(String str);

	/**
	 * get entity details.
	 *
	 * @param id id
	 * @return entity
	 */
	T getEntity(E id);

	/**
	 * create entity.
	 *
	 * @param t t
	 * @return integer
	 */
	T createEntity(T t);

	/**
	 * get entities.
	 *
	 * @param t t
	 * @return Array of Objects
	 */
	Object[] getEntities(E t);
	/**
	 * find record based on userId.
	 * @param userId userId
	 * @return UserInstance
	 */
	User findUserByUserId(String userId);
    /**
     * Checks if mail Already exists in DB.
     * @param e "mailId"
     * @return "true/false"
     */
	boolean isEmailExists(E e);
}
