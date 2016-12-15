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
 * Wrapper for Bulk Job calls
 */
public class BulkJob extends ApiNode {
	@SerializedName("job-ids")
	List<Integer> jobId;
	@SerializedName("success")
	boolean success = false;
	@SerializedName("errors")
	Map<String, String> errors = null;
	@SerializedName("reason")
	String reason = null;

	public static BulkJob parseResponse(String json, ApiContext context, ApiRequest<BulkJob> request)
			throws MalformedResponseException {
		BulkJob batch = getGson().fromJson(json, BulkJob.class);
		batch.context = context;
		batch.rawValue = json;
		return batch;
	}

	/**
	 * List of job ids after attach to batch
	 * 
	 * @return List of job ids
	 */
	public List<Integer> getJobIds() {
		return jobId;
	}

	/**
	 * Reason for failure call
	 * 
	 * @return Reason for failure call
	 */
	public String getReason() {
		return reason;
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

	@Override
	public String toString() {
		return getGson().toJson(this);
	}
}
