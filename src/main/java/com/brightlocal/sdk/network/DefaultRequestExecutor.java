package com.brightlocal.sdk.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import com.brightlocal.sdk.ApiConfig;
import com.brightlocal.sdk.ApiContext;
import com.brightlocal.sdk.exception.ApiException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Default request executor
 */
public class DefaultRequestExecutor implements IRequestExecutor {
	private int lastResponseCode = -1;

	public String execute(String apiUrl, Map<String, Object> allParam, ApiContext context)
			throws ApiException, IOException {
		return execute(apiUrl, ApiConfig.HTTP_METHOD_POST, allParam, context);
	}

	public String execute(String apiUrl, String httpMethod, Map<String, Object> allParams, ApiContext context)
			throws ApiException, IOException {
		if (!ApiConfig.suportedMethods.contains(httpMethod)) {
			throw new IllegalArgumentException(String.format("Unsupported http method. Currently only %s are supported",
					ApiConfig.suportedMethods.toString().replaceAll("\\[|\\]", "")));
		}

		if (ApiConfig.HTTP_METHOD_GET.equals(httpMethod))
			return sendGet(apiUrl, allParams, context);
		else if (ApiConfig.HTTP_METHOD_POST.equals(httpMethod))
			return sendPost(apiUrl, allParams, context);
		else if (ApiConfig.HTTP_METHOD_PUT.equals(httpMethod))
			return sendPut(apiUrl, allParams, context);
		else if (ApiConfig.HTTP_METHOD_DELETE.equals(httpMethod))
			return sendDelete(apiUrl, allParams, context);
		throw new IllegalArgumentException(String.format("Unsupported http method. Currently only %s are supported",
				ApiConfig.suportedMethods.toString().replaceAll("\\[|\\]", "")));

	}

	public String sendGet(String apiUrl, Map<String, Object> allParams, ApiContext context)
			throws ApiException, IOException {
		StringBuilder urlString = new StringBuilder(apiUrl);
		boolean firstEntry = true;
		for (Map.Entry<String, Object> entry : allParams.entrySet()) {
			urlString.append((firstEntry ? "?" : "&") + URLEncoder.encode(entry.getKey(), "UTF-8") + "="
					+ URLEncoder.encode(convertToString(entry.getValue()), "UTF-8"));
			firstEntry = false;
		}
		URL url = new URL(urlString.toString());
		context.log("Request:");
		context.log("GET: " + url.toString());
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		return readResponse(con);
	}

	private String sendPP(String apiUrl, Map<String, Object> allParams, ApiContext context, String httpMethod)
			throws ApiException, IOException {
		httpMethod = httpMethod.toUpperCase();
		URL url = new URL(apiUrl);
		context.log(httpMethod + ": " + url.toString());

		StringBuilder paramString = new StringBuilder();
		boolean firstEntry = true;
		for (Map.Entry<String, Object> entry : allParams.entrySet()) {
			paramString.append((firstEntry ? "?" : "&") + URLEncoder.encode(entry.getKey(), "UTF-8") + "="
					+ URLEncoder.encode(convertToString(entry.getValue()), "UTF-8"));
			firstEntry = false;
		}
		byte[] postData = paramString.toString().getBytes(StandardCharsets.UTF_8);
		int postDataLength = postData.length;

		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

		con.setRequestMethod(httpMethod);
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestProperty("Content-Length", Integer.toString(postDataLength));
		con.setUseCaches(false);
		con.setDoOutput(true);
		con.setDoInput(true);

		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.write(postData);

		return readResponse(con);
	}

	public String sendPost(String apiUrl, Map<String, Object> allParams, ApiContext context)
			throws ApiException, IOException {
		return sendPP(apiUrl, allParams, context, ApiConfig.HTTP_METHOD_POST);
	}

	public String sendPut(String apiUrl, Map<String, Object> allParams, ApiContext context)
			throws ApiException, IOException {
		return sendPP(apiUrl, allParams, context, ApiConfig.HTTP_METHOD_PUT);
	}

	public String sendDelete(String apiUrl, Map<String, Object> allParams, ApiContext context)
			throws ApiException, IOException {
		StringBuilder urlString = new StringBuilder(apiUrl);
		boolean firstEntry = true;
		for (Map.Entry<String, Object> entry : allParams.entrySet()) {
			urlString.append((firstEntry ? "?" : "&") + URLEncoder.encode(entry.getKey(), "UTF-8") + "="
					+ URLEncoder.encode(convertToString(entry.getValue()), "UTF-8"));
			firstEntry = false;
		}
		URL url = new URL(urlString.toString());
		context.log("Delete: " + url.toString());
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

		con.setRequestMethod("DELETE");

		return readResponse(con);
	}

	public int getLastHttpCode() {
		return lastResponseCode;
	}

	private String readResponse(HttpsURLConnection con) throws ApiException, IOException {
		try {
			lastResponseCode = con.getResponseCode();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.toString();
		} catch (Exception e) {

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			throw new ApiException.FailedRequestException(response.toString(), e);
		}
	}

	private static String convertToString(Object input) {
		if (input == null) {
			return "null";
		} else if (input instanceof Map) {
			Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.STATIC)
					.excludeFieldsWithModifiers(Modifier.PROTECTED).disableHtmlEscaping().create();
			return gson.toJson((Map) input);
		} else if (input instanceof List) {
			Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.STATIC)
					.excludeFieldsWithModifiers(Modifier.PROTECTED).disableHtmlEscaping().create();
			return gson.toJson((List) input);
		} else {
			return input.toString();
		}
	}
}
