package com.brightlocal.sdk.exception;

import com.brightlocal.sdk.network.ApiNode;
import com.brightlocal.sdk.network.ApiResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Exception relates to API
 */
public class ApiException extends Exception implements ApiResponse {

	public ApiException() {
		super();
	}

	public ApiException(Throwable e) {
		super(e);
	}

	public ApiException(String message) {
		super(message);
	}

	public ApiException(String message, Throwable e) {
		super(message, e);
	}

	@Override
	public ApiNode head() {
		return null;
	}

	@Override
	public ApiException getException() {
		return this;
	}

	@Override
	public String getRawResponse() {
		return this.getMessage();
	}

	@Override
	public JsonObject getRawResponseAsJsonObject() {
		JsonParser parser = new JsonParser();
		return parser.parse(this.getMessage()).getAsJsonObject();
	}

	/**
	 * Shows that Response is malformed
	 */
	public static class MalformedResponseException extends ApiException {
		public MalformedResponseException() {
			super();
		}

		public MalformedResponseException(Throwable e) {
			super(e);
		}

		public MalformedResponseException(String message) {
			super(message);
		}

		public MalformedResponseException(String message, Throwable e) {
			super(message, e);
		}
	}

	/**
	 * Shows that request failed
	 */
	public static class FailedRequestException extends ApiException {
		public FailedRequestException() {
			super();
		}

		public FailedRequestException(Throwable e) {
			super(e);
		}

		public FailedRequestException(String message) {
			super(message);
		}

		public FailedRequestException(String message, Throwable e) {
			super(message, e);
		}
	}
}
