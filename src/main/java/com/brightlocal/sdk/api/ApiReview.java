package com.brightlocal.sdk.api;

import java.util.Map;

import com.brightlocal.sdk.ApiContext;
import com.brightlocal.sdk.exception.ApiException;
import com.brightlocal.sdk.exception.ApiException.MalformedResponseException;
import com.brightlocal.sdk.network.ApiNode;
import com.brightlocal.sdk.network.ApiRequest;
import com.brightlocal.sdk.network.IResponseParser;
import com.brightlocal.sdk.pojo.Review;
import com.brightlocal.sdk.pojo.ReviewBusinessData;
import com.google.gson.reflect.TypeToken;

/**
 * This API allows you to retrieve reviews.
 */
public class ApiReview extends ApiNode {
	private static final String NODE_ID = "ld";

	public ApiReview(ApiContext context) {
		setContext(context);
	}

	/**
	 * <h2>Fetch Reviews (by profile URL)</h2>
	 * <p>
	 * Authentication for this method is via API key only.
	 * 
	 * @param batch
	 * @param review
	 * @return fetch review job attached to batch
	 * @throws ApiException
	 */
	public Job addFetchReviewsJob(Batch batch, Review review) throws ApiException {
		String json = getGson().toJson(review);
		Map<String, Object> map = getGson().fromJson(json, new TypeToken<Map<String, Object>>() {
		}.getType());

		Job response = new ApiRequest<Job>(context, NODE_ID + "/fetch-reviews", new IResponseParser<Job>() {
			@Override
			public Job parseResponse(String json, ApiContext context, ApiRequest<Job> request)
					throws MalformedResponseException {
				return Job.parseResponse(json, context, request);
			}
		}).setParams(map).setParam("batch-id", "" + batch.getId()).execute();

		return response;
	}

	/**
	 * <h2>Fetch Reviews (by business data)</h2>
	 * <p>
	 * Authentication for this method is via API key only.
	 * 
	 * @param batch
	 * @param review
	 * @return fetch review job attached to batch
	 * @throws ApiException
	 */
	public Job addFetchReviewsJob(Batch batch, ReviewBusinessData review) throws ApiException {
		String json = getGson().toJson(review);
		Map<String, Object> map = getGson().fromJson(json, new TypeToken<Map<String, Object>>() {
		}.getType());

		Job response = new ApiRequest<Job>(context, NODE_ID + "/fetch-reviews-by-business-data",
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
