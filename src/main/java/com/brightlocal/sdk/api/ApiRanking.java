package com.brightlocal.sdk.api;

import java.util.Map;

import com.google.gson.reflect.TypeToken;

import com.brightlocal.sdk.ApiContext;
import com.brightlocal.sdk.exception.ApiException;
import com.brightlocal.sdk.exception.ApiException.MalformedResponseException;
import com.brightlocal.sdk.network.ApiNode;
import com.brightlocal.sdk.network.ApiRequest;
import com.brightlocal.sdk.network.IResponseParser;
import com.brightlocal.sdk.pojo.BulkSearch;
import com.brightlocal.sdk.pojo.Search;

/**
 * This API allows you to retrieve search ranking (and listing data) from the
 * major search engines and their local variants, <br>
 * namely Google, Google Maps, Yahoo!, Yahoo! Local, Bing and Bing Maps.
 */
public class ApiRanking extends ApiNode {
	private static final String NODE_ID = "rankings";

	public ApiRanking(ApiContext context) {
		setContext(context);
	}

	/**
	 * This API method allows you to retrieve search ranking (and listing data)
	 * from the major search engines and their local variants, <br>
	 * namely Google, Google Maps, Yahoo!, Yahoo! Local, Bing and Bing Maps.
	 * <br>
	 * It works for the USA, United Kingdom, Canada and Australia. The only
	 * exception is in Australia where Yahoo! Local is not supported.
	 * <p>
	 * This method needs to be used in conjunction with the
	 * <a href="http://apidocs.brightlocal.com/#batches">batch methods</a>
	 * described above.
	 * 
	 * @param batch
	 * @param search
	 * @return search job attached to batch
	 * @throws ApiException
	 */
	public Job addSearchJob(Batch batch, Search search) throws ApiException {
		String json = getGson().toJson(search);
		Map<String, Object> map = getGson().fromJson(json, new TypeToken<Map<String, Object>>() {
		}.getType());

		Job response = new ApiRequest<Job>(context, NODE_ID + "/search", new IResponseParser<Job>() {
			@Override
			public Job parseResponse(String json, ApiContext context, ApiRequest<Job> request)
					throws MalformedResponseException {
				return Job.parseResponse(json, context, request);
			}
		}).setParams(map).setParam("batch-id", "" + batch.getId()).execute();

		return response;
	}

	/**
	 * This method works the same as the search method above except it allows
	 * you to submit up to 100 search terms in one request. <br>
	 * Use this when you want to look up rankings for many hundreds or thousands
	 * of search terms.
	 * 
	 * @param batch
	 * @param bulkSearch
	 * @return bulk search job attached to batch
	 * @throws ApiException
	 */
	public BulkJob addBulkSearchJob(Batch batch, BulkSearch bulkSearch) throws ApiException {
		String json = getGson().toJson(bulkSearch);
		Map<String, Object> map = getGson().fromJson(json, new TypeToken<Map<String, Object>>() {
		}.getType());

		BulkJob response = new ApiRequest<BulkJob>(context, NODE_ID + "/bulk-search", new IResponseParser<BulkJob>() {
			@Override
			public BulkJob parseResponse(String json, ApiContext context, ApiRequest<BulkJob> request)
					throws MalformedResponseException {
				return BulkJob.parseResponse(json, context, request);
			}
		}).setParams(map).setParam("batch-id", "" + batch.getId()).execute();

		return response;
	}

}
