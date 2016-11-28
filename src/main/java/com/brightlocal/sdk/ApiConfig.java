package com.brightlocal.sdk;

import java.util.ArrayList;
import java.util.List;

/**
 * API configuration.
 */
public class ApiConfig {
	public static final String DEFAULT_API_VERSION = "v4";
	/** API endpoint URL */
	public static final String DEFAULT_ENDPOINT = "https://tools.brightlocal.com/seo-tools/api";
	/** expiry can't be more than 30 minutes (1800 seconds) */
	public static final int MAX_EXPIRY = 1800;

	public static final String HTTP_METHOD_POST = "post";
	public static final String HTTP_METHOD_GET = "get";
	public static final String HTTP_METHOD_PUT = "put";
	public static final String HTTP_METHOD_DELETE = "delete";

	public static List<String> suportedMethods = new ArrayList<>();
	static {
		suportedMethods.add(HTTP_METHOD_POST);
		suportedMethods.add(HTTP_METHOD_GET);
		suportedMethods.add(HTTP_METHOD_PUT);
		suportedMethods.add(HTTP_METHOD_DELETE);
	}
}
