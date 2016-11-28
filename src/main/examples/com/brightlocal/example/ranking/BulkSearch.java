package com.brightlocal.example.ranking;

import java.util.ArrayList;
import java.util.List;

import com.brightlocal.sdk.ApiContext;
import com.brightlocal.sdk.api.ApiBatch;
import com.brightlocal.sdk.api.ApiRanking;
import com.brightlocal.sdk.api.Batch;
import com.brightlocal.sdk.api.BatchResults;
import com.brightlocal.sdk.api.BulkJob;
import com.brightlocal.sdk.exception.ApiException;

/**
 * Example for showing BulkSearch API call.
 */
public class BulkSearch {

	public static final String API_KEY = "API_KEY_HERE";
	public static final String API_SECRET = "API_SECRET_HERE";

	public static void main(String[] args) {
		ApiContext context = new ApiContext(API_KEY, API_SECRET);
		context.enableDebug(true);

		// Initialize sdk
		ApiBatch apiBatch = new ApiBatch(context);
		ApiRanking apiRanking = new ApiRanking(context);

		// Prepare test search
		List<String> searches = new ArrayList<>();
		searches.add("restaurant new york");
		searches.add("restaurant manhattan");
		searches.add("restaurant 10019");

		com.brightlocal.sdk.pojo.BulkSearch bulkSearch = new com.brightlocal.sdk.pojo.BulkSearch("google", "USA",
				searches);
		bulkSearch.setGoogleLocation("New York, NY");
		bulkSearch.addUrl("le-bernardin.com");
		bulkSearch.addBusinessNames("Le Bernardin");

		try {
			// Create a new batch
			Batch batch = apiBatch.create();
			if (batch.isSuccess()) {
				// If batch was successfully created, ad jobs
				context.log("Created batch ID " + batch.getId());

				BulkJob job = apiRanking.addBulkSearchJob(batch, bulkSearch);
				if (job.isSuccess()) {
					context.log("Added jobs with ID " + job.getJobIds());
				}

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
