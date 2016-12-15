package com.brightlocal.sdk.api;

import java.util.Map;

import com.brightlocal.sdk.ApiContext;
import com.brightlocal.sdk.exception.ApiException;
import com.brightlocal.sdk.exception.ApiException.MalformedResponseException;
import com.brightlocal.sdk.network.ApiNode;
import com.brightlocal.sdk.network.ApiRequest;
import com.brightlocal.sdk.network.IResponseParser;
import com.brightlocal.sdk.pojo.SocialProfile;
import com.google.gson.reflect.TypeToken;

/**
 * This API returns offsite SEO information domain age, hosting location, number
 * of pages indexed and authority.
 */
public class ApiOffsiteSeo extends ApiNode {
	private static final String NODE_ID = "seo";

	public ApiOffsiteSeo(ApiContext context) {
		setContext(context);
	}

	/**
	 * <h2>Offsite SEO</h2>
	 * <p>
	 * This API method returns offsite SEO information domain age, hosting
	 * location, number of pages indexed and authority. <br>
	 * Authentication for this method is via API key only.
	 * 
	 * @param batch
	 * @param websiteUrl
	 * @return offsite job attached to batch
	 * @throws ApiException
	 */
	public Job offsite(Batch batch, String websiteUrl) throws ApiException {

		Job response = new ApiRequest<Job>(context, NODE_ID + "/offsite", new IResponseParser<Job>() {
			@Override
			public Job parseResponse(String json, ApiContext context, ApiRequest<Job> request)
					throws MalformedResponseException {
				return Job.parseResponse(json, context, request);
			}
		}).setParam("batch-id", "" + batch.getId()).setParam("website-url", websiteUrl).execute();

		return response;
	}

	/**
	 * <h2>Social Profiles</h2>
	 * <p>
	 * This API method returns details of the social profiles a business has on
	 * Twitter, Facebook and Foursquare. <br>
	 * Authentication for this method is via API key only.
	 * <p>
	 * Please Note: Address and telephone fields are mostly optional but the
	 * more information you provide the more likely that we’ll return correct
	 * matches for your business.
	 * 
	 * @param batch
	 * @param socialProfile
	 * @return social profiles job attached to batch
	 * @throws ApiException
	 */
	public Job socialProfiles(Batch batch, SocialProfile socialProfile) throws ApiException {
		String json = getGson().toJson(socialProfile);
		Map<String, Object> map = getGson().fromJson(json, new TypeToken<Map<String, Object>>() {
		}.getType());

		Job response = new ApiRequest<Job>(context, "social/profiles", new IResponseParser<Job>() {
			@Override
			public Job parseResponse(String json, ApiContext context, ApiRequest<Job> request)
					throws MalformedResponseException {
				return Job.parseResponse(json, context, request);
			}
		}).setParams(map).setParam("batch-id", "" + batch.getId()).execute();

		return response;
	}
}
