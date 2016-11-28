package com.brightlocal.sdk.api;

import java.util.Map;

import com.brightlocal.sdk.ApiContext;
import com.brightlocal.sdk.exception.ApiException;
import com.brightlocal.sdk.exception.ApiException.MalformedResponseException;
import com.brightlocal.sdk.network.ApiNode;
import com.brightlocal.sdk.network.ApiRequest;
import com.brightlocal.sdk.network.IResponseParser;
import com.brightlocal.sdk.pojo.ProfileUrl;
import com.google.gson.reflect.TypeToken;

/**
 * This API allows you to fetch Profile Details.
 */
public class ApiLocalDirectory extends ApiNode {
	private static final String NODE_ID = "ld";

	public ApiLocalDirectory(ApiContext context) {
		setContext(context);
	}

	/**
	 * <h2>Fetch Profile URL</h2>
	 * <p>
	 * Authentication for this method is via API key only.
	 * 
	 * @param batch
	 * @param profileUrl
	 * @return fetch profile job attached to batch
	 * @throws ApiException
	 */
	public Job addFetchProfileUrlJob(Batch batch, ProfileUrl profileUrl) throws ApiException {
		String json = getGson().toJson(profileUrl);
		Map<String, Object> map = getGson().fromJson(json, new TypeToken<Map<String, Object>>() {
		}.getType());

		Job response = new ApiRequest<Job>(context, NODE_ID + "/fetch-profile-url", new IResponseParser<Job>() {
			@Override
			public Job parseResponse(String json, ApiContext context, ApiRequest<Job> request)
					throws MalformedResponseException {
				return Job.parseResponse(json, context, request);
			}
		}).setParams(map).setParam("batch-id", "" + batch.getId()).execute();

		return response;
	}

	/**
	 * <h2>Fetch Profile URL (Telephone Number Only)</h2>
	 * <p>
	 * Authentication for this method is via API key only.
	 * 
	 * @param batch
	 * @param localDirectory
	 * @param telephone
	 * @return fetch profile job attached to batch
	 * @throws ApiException
	 */
	public Job addFetchProfileUrlJob(Batch batch, String localDirectory, String telephone) throws ApiException {

		Job response = new ApiRequest<Job>(context, NODE_ID + "/fetch-profile-url", new IResponseParser<Job>() {
			@Override
			public Job parseResponse(String json, ApiContext context, ApiRequest<Job> request)
					throws MalformedResponseException {
				return Job.parseResponse(json, context, request);
			}
		}).setParam("batch-id", "" + batch.getId()).setParam("local-directory", localDirectory)
				.setParam("telephone", telephone).setParam("search-type", "search-by-phone").execute();

		return response;
	}

	/**
	 * <h2>Fetch Profile Details (by profile URL)</h2>
	 * <p>
	 * Authentication for this method is via API key only.
	 * 
	 * @param batch
	 * @param profileUrl
	 * @param country
	 * @return fetch profile job attached to batch
	 * @throws ApiException
	 */
	public Job addFetchProfileDetailsJob(Batch batch, String profileUrl, String country) throws ApiException {

		Job response = new ApiRequest<Job>(context, NODE_ID + "/fetch-profile-details", new IResponseParser<Job>() {
			@Override
			public Job parseResponse(String json, ApiContext context, ApiRequest<Job> request)
					throws MalformedResponseException {
				return Job.parseResponse(json, context, request);
			}
		}).setParam("batch-id", "" + batch.getId()).setParam("profile-url", profileUrl).setParam("country", country)
				.execute();

		return response;
	}

	/**
	 * <h2>Fetch Profile Details (by business data)</h2>
	 * <p>
	 * This method shortcuts Fetch Profile URL and Fetch Profile Details above
	 * by carrying out both in one step. <br>
	 * It essentially looks up the URL and then uses that to fetch the profile
	 * details.
	 * 
	 * @param batch
	 * @param profileUrl
	 * @return fetch profile job attached to batch
	 * @throws ApiException
	 */
	public Job addFetchProfileDetailsJob(Batch batch, ProfileUrl profileUrl) throws ApiException {
		String json = getGson().toJson(profileUrl);
		Map<String, Object> map = getGson().fromJson(json, new TypeToken<Map<String, Object>>() {
		}.getType());

		Job response = new ApiRequest<Job>(context, NODE_ID + "/fetch-profile-details-by-business-data",
				new IResponseParser<Job>() {
					@Override
					public Job parseResponse(String json, ApiContext context, ApiRequest<Job> request)
							throws MalformedResponseException {
						return Job.parseResponse(json, context, request);
					}
				}).setParams(map).setParam("batch-id", "" + batch.getId()).execute();

		return response;
	}
}
