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
 * Wrapper for job calls
 */
public class Job extends ApiNode {
	@SerializedName("job-id")
	int jobId;
	@SerializedName("success")
	boolean success = false;
	@SerializedName("errors")
	Map<String, String> errors = null;
	@SerializedName("reason")
	String reason = null;

	public static Job parseResponse(String json, ApiContext context, ApiRequest<Job> request)
			throws MalformedResponseException {
		Job batch = getGson().fromJson(json, Job.class);
		batch.context = context;
		batch.rawValue = json;
		return batch;
	}

	/**
	 * Job id after attach to batch
	 * 
	 * @return job-id
	 */
	public int getJobId() {
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