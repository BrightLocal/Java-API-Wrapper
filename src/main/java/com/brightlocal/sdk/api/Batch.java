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
 * Wrapper for batch calls 
 */
public class Batch extends ApiNode {
	@SerializedName("batch-id")
	int id;
	@SerializedName("success")
	private boolean success = false;
	@SerializedName("errors")
	private Map<String, String> errors = null;

	public static Batch parseResponse(String json, ApiContext context, ApiRequest<Batch> request)
			throws MalformedResponseException {
		Batch batch = getGson().fromJson(json, Batch.class);
		batch.context = context;
		batch.rawValue = json;
		return batch;
	}
	/**
	 * Batch id
	 * @return batch-id
	 */
	public int getId() {
		return id;
	}
	/**
	 * Whether batch call was success
	 * @return true or false
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * List of errors if batch call was unsuccessful 
	 * @return list of errors
	 */
	public List<String> getErrors() {
		List<String> eList = new ArrayList<>();
		for (Map.Entry<String, String> entry : errors.entrySet()) {
			eList.add(entry.getKey()+":"+entry.getValue());
		}
		return eList;
	}
	
	@Override
	public String toString() {
		return getGson().toJson(this);
	}
}
