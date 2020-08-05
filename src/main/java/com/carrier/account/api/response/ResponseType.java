package com.carrier.account.api.response;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;

/**
 * Class to define response formation methods.
 *
 */
public class ResponseType {

	/**
	 * Formation of response for success case.
	 *
	 * @param status  status
	 * @param message message
	 * @param url     url
	 * @return JsonObject
	 */
	public static JSONObject successResponse(HttpStatus status, String message, String url) {
		JSONObject opt = new JSONObject();
		String[] arr = status.toString().split(" ", 2);
		opt.put("message", message);
		opt.put("status", arr[0]);
		opt.put("error", arr[1]);
		opt.put("path", url);
		return opt;

	}

}
