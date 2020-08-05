package com.carrier.account.api.response;
import lombok.Builder;
import lombok.Data;
/**
 *
 * @author Nanda.Kishore
 * Message Response Holder
 * @param <T> AnyType
 */
@Data
@Builder
public class MessageResponse<T> {
	/**
	 * Sets Message.
	 */
	private String message;
	/**
	 * Sets status.
	 */
	private String status;
	/**
	 * Sets details.
	 */
	private T details;
}