package com.brightlocal.example.seo;

import com.brightlocal.sdk.ApiContext;
import com.brightlocal.sdk.api.ApiBatch;
import com.brightlocal.sdk.api.ApiOffsiteSeo;
import com.brightlocal.sdk.api.Batch;
import com.brightlocal.sdk.api.BatchResults;
import com.brightlocal.sdk.api.Job;
import com.brightlocal.sdk.exception.ApiException;

/**
 * Example for showing OffsiteSeo API call.
 */
public class OffsiteSeo {
	public static final String API_KEY = "API_KEY_HERE";
	public static final String API_SECRET = "API_SECRET_HERE";

	public static void main(String[] args) {
		ApiContext context = new ApiContext(API_KEY, API_SECRET);
		context.enableDebug(true);

		// Initialize sdk
		ApiBatch apiBatch = new ApiBatch(context);
		ApiOffsiteSeo apiOffsiteSeo = new ApiOffsiteSeo(context);

		try {
			// Step 1: Create a new batch
			Batch batch = apiBatch.create();
			if (batch.isSuccess()) {
				// If batch was successfully created, ad jobs
				context.log("Created batch ID " + batch.getId());

				// Step 2: Add offsite job to batch
				Job job = apiOffsiteSeo.offsite(batch, "http://www.gramercytavern.com");
				if (job.isSuccess()) {
					context.log("Added job with ID " + job.getJobId());
				}
				// Step 3: Commit batch (to signal all jobs added, processing
				// starts)
				batch = apiBatch.commit(batch);
				if (batch.isSuccess()) {
					context.log("Committed batch successfully.");

					// poll for results, in a real world example you might
					// want to do this in a separate thread
					BatchResults batchResults;
					do {
						batchResults = apiBatch.getResults(batch);

						try {
							Thread.sleep(5000); // limit how often you poll
						} catch (InterruptedException e) {
						}
					} while (!"Stopped".equals(batchResults.getStatus())
							&& !"Finished".equals(batchResults.getStatus()));

					// Prints result
					context.log(batchResults.getRawResponse());
				}
			}
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}

}
