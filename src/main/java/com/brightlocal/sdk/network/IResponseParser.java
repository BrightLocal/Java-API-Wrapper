package com.brightlocal.sdk.network;

import com.brightlocal.sdk.ApiContext;
import com.brightlocal.sdk.exception.ApiException;

/**
 * API response parser
 */
public interface IResponseParser<T extends ApiNode> {
	public T parseResponse(String response, ApiContext context, ApiRequest<T> request)
			throws ApiException.MalformedResponseException;
}
