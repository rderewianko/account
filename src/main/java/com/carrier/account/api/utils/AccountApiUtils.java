package com.carrier.account.api.utils;
import org.springframework.http.ResponseEntity;
import com.carrier.account.api.response.MessageResponse;

/**
 * @author Nanda.Kishore
 *
 */
public final class AccountApiUtils {

	/**
	 * To avoid inheriting to other class and instantiate from other classes.
	 */
	private AccountApiUtils() {
	}
	/**
	 * Builds Response Object to Client.
	 * @param <T> AnyType
	 * @param status status
	 * @param message message
	 * @param details details
	 * @return ResponseEntity
	 */
	public static <T> ResponseEntity<MessageResponse<Object>> buildResponse(String status, String message, T details) {
		MessageResponse<Object> messageResponse = MessageResponse.builder()
                .status(status)
                .message(message)
                .details(details)
                .build();
	  return ResponseEntity.ok(messageResponse);
	}
	/**
	 * Build response object.
	 * @param <T> anyType
	 * @param status status
	 * @param message message
	 * @param details details
	 * @return Messageresponse object
	 */
	public static <T> MessageResponse<Object> postMethodResponse(String status, String message, T details) {
		return MessageResponse.builder()
                .status(status)
                .message(message)
                .details(details)
                .build();
	}
}
