package com.carrier.account.api.exception;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.carrier.account.api.response.MessageResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Nanda.Kishore
 * Global Exception Handler.
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class APIExceptionHandler extends ResponseEntityExceptionHandler {
/**
 * Handles ConstraintViolationExceptions and sends custom defined error message.
 * @param ex exception
 * @param request WebRequest
 * @return ResponseEntity
 */
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		log.error(ex.getMessage(), ex);
		List<String> details = ex.getConstraintViolations()
                 .parallelStream()
                 .map(e -> e.getMessage())
                 .collect(Collectors.toList());
		MessageResponse<Object> errorResponse = MessageResponse.builder()
                .status("Failed")
                .message(HttpStatus.BAD_REQUEST.toString())
                .details(details)
				.build();
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
/**
 * Handles All Exceptions.
 * @param ex ex
 * @param request request
 * @return ResponseEntity
 */
	@ExceptionHandler(InvalidDataAccessResourceUsageException.class)
	public final ResponseEntity<Object> handleGenericException(InvalidDataAccessResourceUsageException ex, WebRequest request) {
		log.error(ex.getMessage(), ex);
		MessageResponse<Object> errorResponse = MessageResponse.builder()
                .status("Failed")
                .message(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .details("Internal Server Errror")
				.build();
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	/**
	 * Handles Custom Field Validations.
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	    List<String> errors = new ArrayList<String>();
	    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
	        errors.add(error.getField() + ": " + error.getDefaultMessage());
	    }
	    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
	        errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
	    }
	    MessageResponse<Object> errorResponse = MessageResponse.builder()
                .status("Failed")
                .message(HttpStatus.BAD_REQUEST.toString())
                .details(errors)
                .build();
	    return handleExceptionInternal(ex, errorResponse, headers, HttpStatus.BAD_REQUEST, request);
	}
}