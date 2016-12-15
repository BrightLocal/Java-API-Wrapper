package com.brightlocal.example.ld;

import java.util.ArrayList;
import java.util.List;

import com.brightlocal.sdk.ApiContext;
import com.brightlocal.sdk.api.ApiBatch;
import com.brightlocal.sdk.api.ApiLocalDirectory;
import com.brightlocal.sdk.api.Batch;
import com.brightlocal.sdk.api.BatchResults;
import com.brightlocal.sdk.api.Job;
import com.brightlocal.sdk.exception.ApiException;
import com.brightlocal.sdk.pojo.ProfileUrl;

/**
 * Example for showing FetchUrls API call.
 */
public class FetchUrls {

	public static final String API_KEY = "API_KEY_HERE";
	public static final String API_SECRET = "API_SECRET_HERE";

	public static void main(String[] args) {
		ApiContext context = new ApiContext(API_KEY, API_SECRET);
		context.enableDebug(true);

		// Initialize sdk
		ApiBatch apiBatch = new ApiBatch(context);
		ApiLocalDirectory apiLD = new ApiLocalDirectory(context);

		// Prepare test searches
		List<ProfileUrl> profileUrls = new ArrayList<>();
		ProfileUrl profileUrl = new ProfileUrl("Eleven Madison Park", "USA", "New York", "10010", "google");
		profileUrls.add(profileUrl);
		profileUrl = new ProfileUrl("Eleven Madison Park", "USA", "New York", "10010", "citysearch");
		profileUrls.add(profileUrl);
		profileUrl = new ProfileUrl("Eleven Madison Park", "USA", "New York", "10010", "dexknows");
		profileUrls.add(profileUrl);
		profileUrl = new ProfileUrl("Eleven Madison Park", "USA", "New York", "10010", "kudzu");
		profileUrls.add(profileUrl);
		profileUrl = new ProfileUrl("Eleven Madison Park", "USA", "New York", "10010", "manta");
		profileUrls.add(profileUrl);

		try {
			// Step 1: Create a new batch
			Batch batch = apiBatch.create();
			if (batch.isSuccess()) {
				// If batch was successfully created, ad jobs
				context.log("Created batch ID " + batch.getId());

				// Step 2: Add directory jobs to batch
				for (ProfileUrl profileUrl2 : profileUrls) {
					Job job = apiLD.addFetchProfileUrlJob(batch, profileUrl2);
					if (job.isSuccess()) {
						context.log("Added job with ID " + job.getJobId());
					}
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
