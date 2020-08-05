package com.carrier.account.api.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Nanda.Kishore
 *
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceConflictException extends Exception {
	/**
	 * Initialization of SerialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Initialization of Logger service.
	 */
	private static Logger logger = LogManager.getLogger(ResourceConflictException.class);

	/**
	 * Initialization of exception.
	 *
	 * @param message message
	 */
	public ResourceConflictException(String message) {
		super(message);
		logger.info("ResourceConflictException - Message : " + message);
	}
}
