package com.brightlocal.sdk.network;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.brightlocal.sdk.ApiConfig;
import com.brightlocal.sdk.ApiContext;
import com.brightlocal.sdk.exception.ApiException;

/**
 * API request wrapper
 */
public class ApiRequest<T extends ApiNode> {

	private static IRequestExecutor executor = new DefaultRequestExecutor();

	protected ApiContext context;
	protected String nodeId;
	protected String httpMethod;
	private T lastResponse = null;
	protected IResponseParser<T> parser;
	protected Map<String, Object> params = new HashMap<>();

	public static void changeRequestExecutor(IRequestExecutor newExecutor) {
		executor = newExecutor;
	}

	public static IRequestExecutor getExecutor() {
		return executor;
	}

	public ApiRequest(ApiContext context, String nodeId, IResponseParser<T> parser) {
		this(context, nodeId, ApiConfig.HTTP_METHOD_POST, parser);
	}

	public ApiRequest(ApiContext context, String nodeId, String method, IResponseParser<T> parser) {
		this.context = context;
		this.nodeId = nodeId;
		this.httpMethod = method;
		this.parser = parser;
	}

	public ApiResponse getLastResponse() {
		return lastResponse;
	};

	public T parseResponse(String response) throws ApiException {
		if (parser != null) {
			return parser.parseResponse(response, context, this);
		}
		return null; 
	};

	public T execute() throws ApiException {
		return execute(new HashMap<String, Object>());
	};

	public T execute(Map<String, Object> extraParams) throws ApiException {
		lastResponse = parseResponse(executeInternal(extraParams));
		return lastResponse;
	};

	public ApiRequest<T> setParam(String param, String value) {
		setParamInternal(param, value);
		return this;
	}

	public ApiRequest<T> setParams(Map<String, Object> params) {
		setParamsInternal(params);
		return this;
	}

	protected String executeInternal() throws ApiException {
		return executeInternal(null);
	}

	protected String executeInternal(Map<String, Object> extraParams) throws ApiException {
		// extraParams are one-time params for this call,
		// so that the APIRequest can be reused later on.
		String response = null;
		try {
			context.log("========Start of API Call========");
			response = executor.execute(getApiUrl(), httpMethod, getAllParams(extraParams), context);
			context.log("Response:");
			context.log(response);
			context.log("========End of API Call========");
		} catch (IOException e) {
			throw new ApiException.FailedRequestException(e);
		}
		return response;
	}

	private Map<String, Object> getAllParams(Map<String, Object> extraParams) {
		// some methods only require api key but there's no harm in also sending
		// sig and expires to those methods
		Map<String, Object> map = context.getSigAndExpires();
		map.put("api-key", context.getApiKey());
		map.putAll(params);
		if (extraParams != null)
			map.putAll(extraParams);
		return map;
	}

	public ApiContext getContext() {
		return this.context;
	}

	public void setContext(ApiContext context) {
		this.context = context;
	}

	protected void setParamInternal(String param, Object value) {
		params.put(param, value);
	}

	protected void setParamsInternal(Map<String, Object> params) {
		this.params.putAll(params);
	}

	private String getApiUrl() {
		String endpointBas = context.getEndpoint();
		return endpointBas + "/" + context.getVersion() + "/" + nodeId;
	}

}
