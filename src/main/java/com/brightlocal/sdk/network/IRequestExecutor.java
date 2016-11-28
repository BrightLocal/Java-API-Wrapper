package com.brightlocal.sdk.network;

import java.io.IOException;
import java.util.Map;

import com.brightlocal.sdk.ApiContext;
import com.brightlocal.sdk.exception.ApiException;

/**
 * APi executor wrapper
 */
public interface IRequestExecutor {
	public String execute(String apiUrl, Map<String, Object> allParams, ApiContext context)
			throws ApiException, IOException;

	public String execute(String apiUrl, String httpMethod, Map<String, Object> allParams, ApiContext context)
			throws ApiException, IOException;

	public String sendGet(String apiUrl, Map<String, Object> allParams, ApiContext context)
			throws ApiException, IOException;

	public String sendPost(String apiUrl, Map<String, Object> allParams, ApiContext context)
			throws ApiException, IOException;

	public String sendDelete(String apiUrl, Map<String, Object> allParams, ApiContext context)
			throws ApiException, IOException;
}
