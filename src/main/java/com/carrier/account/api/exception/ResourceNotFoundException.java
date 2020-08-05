package com.carrier.account.api.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

/**
 * Defining ResourceNotFoundException.
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	/**
	 * Initialization of SerialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Initialization of Logger service.
	 */
	private static Logger logger = LogManager.getLogger(ResourceNotFoundException.class);

	/**
	 * Initialization of exception.
	 *
	 * @param message message
	 */
	public ResourceNotFoundException(String message) {
		super(message);
		logger.info("ResourceNotFoundException - message : " + message);
	}
}
