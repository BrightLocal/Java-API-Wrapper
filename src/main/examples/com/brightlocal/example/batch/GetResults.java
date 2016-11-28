package com.brightlocal.example.batch;

import com.brightlocal.sdk.ApiContext;
import com.brightlocal.sdk.api.ApiBatch;
import com.brightlocal.sdk.api.Batch;
import com.brightlocal.sdk.api.BatchResults;
import com.brightlocal.sdk.exception.ApiException;

/**
 * Example for showing GetResults API call.
 */
public class GetResults {
	public static final String API_KEY = "API_KEY_HERE";
	public static final String API_SECRET = "API_SECRET_HERE";

	public static void main(String[] args) {
		ApiContext context = new ApiContext(API_KEY, API_SECRET);
		context.enableDebug(true);

		// Initialize sdk
		ApiBatch apiBatch = new ApiBatch(context);

		try {
			// Create a new batch
			Batch batch = apiBatch.create();
			if (batch.isSuccess()) {
				int batchId = batch.getId();
				// If batch was successfully created, request batch results
				BatchResults batchResults = apiBatch.getResults(batch);
				if (batchResults.isSuccess()) {
					// process batch results here
				}
				batch = apiBatch.stop(batch);
				batch = apiBatch.delete(batch);
			}
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}
}
