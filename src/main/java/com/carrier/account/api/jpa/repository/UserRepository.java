package com.carrier.account.api.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.carrier.account.api.jpa.entity.User;

/**
 * User repository to initiate DB connection.
 *
 */
public interface UserRepository extends JpaRepository<User, String> {

	/**
	 * method to find available of user.
	 * @param userId userId
	 * @return boolean
	 */
	boolean existsUserByUserId(String userId);

	/**
	 * Gets User details from the DB.
	 * @param userId userId
	 * @return userInstance
	 */
	@Query("SELECT u FROM User u WHERE u.userId = ?1")
	User findUserByUserId(String userId);

	/**
	 * method to find available of emailAddress.
	 * @param emailAddress email
	 * @return boolean
	 */
	boolean existsUserByEmailAddress(String emailAddress);
}
