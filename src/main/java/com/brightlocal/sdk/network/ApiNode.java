package com.brightlocal.sdk.network;

import java.lang.reflect.Modifier;

import com.brightlocal.sdk.ApiContext;
import com.brightlocal.sdk.exception.ApiException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Base node of API requests and results
 */
public class ApiNode implements ApiResponse {

	protected static Gson gson = null;
	protected ApiContext context = null;
	protected String rawValue = null;

	public static ApiNode loadJSON(String json, ApiContext context) {
		ApiNode result = null;
		result = new ApiNode();
		result.context = context;
		result.rawValue = json;
		return result;
	}

	public ApiContext getContext() {
		return context;
	}

	public void setContext(ApiContext context) {
		this.context = context;
	}

	@Override
	public String toString() {
		return rawValue;
	}

	@Override
	public String getRawResponse() {
		return rawValue;
	}

	@Override
	public JsonObject getRawResponseAsJsonObject() {
		JsonParser parser = new JsonParser();
		try {
			return parser.parse(rawValue).getAsJsonObject();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ApiNode head() {
		return this;
	}

	@Override
	public ApiException getException() {
		return null;
	}

	protected synchronized static Gson getGson() {
		if (gson != null) {
			return gson;
		} else {
			gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.STATIC)
					.excludeFieldsWithModifiers(Modifier.PROTECTED).disableHtmlEscaping().create();
		}
		return gson;
	}
}
