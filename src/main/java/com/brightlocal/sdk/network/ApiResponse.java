package com.brightlocal.sdk.network;

import com.brightlocal.sdk.exception.ApiException;
import com.google.gson.JsonObject;

/**
 * API response wrapper
 */
public interface ApiResponse {
	public String getRawResponse();

	public JsonObject getRawResponseAsJsonObject();

	public ApiNode head();

	public ApiException getException();
}