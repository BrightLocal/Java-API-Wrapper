package com.brightlocal.sdk.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.brightlocal.sdk.ApiContext;
import com.brightlocal.sdk.exception.ApiException.MalformedResponseException;
import com.brightlocal.sdk.network.ApiNode;
import com.brightlocal.sdk.network.ApiRequest;
import com.google.gson.annotations.SerializedName;

/**
 * Wrapper for the results of all jobs added to the batch.
 */
public class BatchResults extends ApiNode {
	@SerializedName("success")
	boolean success = false;
	@SerializedName("errors")
	Map<String, String> errors = null;
	@SerializedName("status")
	String status = null;
	@SerializedName("statuses")
	Map<String, String> statuses = null;

	public static BatchResults parseResponse(String json, ApiContext context, ApiRequest<BatchResults> request)
			throws MalformedResponseException {
		BatchResults batch = getGson().fromJson(json, BatchResults.class);
		batch.context = context;
		batch.rawValue = json;
		return batch;
	}

	/**
	 * Whether batch call was success
	 * 
	 * @return true or false
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * List of errors if batch call was unsuccessful
	 * 
	 * @return list of errors
	 */
	public List<String> getErrors() {
		List<String> eList = new ArrayList<>();
		for (Map.Entry<String, String> entry : errors.entrySet()) {
			eList.add(entry.getKey() + ":" + entry.getValue());
		}
		return eList;
	}

	/**
	 * Status of batch. Once results for all jobs have been returned the batch
	 * will be marked as “Finished”.
	 * 
	 * @return Status of batch
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Shows jobs statuses attached to this batch.
	 * 
	 * @return jobs statuses
	 */
	public Map<String, String> getStatuses() {
		return statuses;
	}

	@Override
	public String toString() {
		return getGson().toJson(this);
	}
}
