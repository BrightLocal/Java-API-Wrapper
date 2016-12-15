package com.brightlocal.example.ranking;

import java.util.ArrayList;
import java.util.List;

import com.brightlocal.sdk.ApiContext;
import com.brightlocal.sdk.api.ApiBatch;
import com.brightlocal.sdk.api.ApiRanking;
import com.brightlocal.sdk.api.Batch;
import com.brightlocal.sdk.api.BatchResults;
import com.brightlocal.sdk.api.Job;
import com.brightlocal.sdk.exception.ApiException;

/**
 * Example for showing Search API call.
 */
public class Search {
	public static final String API_KEY = "API_KEY_HERE";
	public static final String API_SECRET = "API_SECRET_HERE";

	public static void main(String[] args) {
		ApiContext context = new ApiContext(API_KEY, API_SECRET);
		context.enableDebug(true);

		// Initialize sdk
		ApiBatch apiBatch = new ApiBatch(context);
		ApiRanking apiRanking = new ApiRanking(context);

		// Prepare test searches
		List<com.brightlocal.sdk.pojo.Search> searches = new ArrayList<>();
		com.brightlocal.sdk.pojo.Search search = new com.brightlocal.sdk.pojo.Search("google", "USA",
				"restaurant new york");
		search.setGoogleLocation("New York, NY");
		search.addUrl("le-bernardin.com");
		search.addBusinessNames("Le Bernardin");
		searches.add(search);

		search = new com.brightlocal.sdk.pojo.Search("google", "USA", "estaurant manhattan");
		search.setGoogleLocation("New York, NY");
		search.addUrl("le-bernardin.com");
		search.addBusinessNames("Le Bernardin");
		searches.add(search);

		search = new com.brightlocal.sdk.pojo.Search("google", "USA", "restaurant 10019");
		search.setGoogleLocation("New York, NY");
		search.addUrl("le-bernardin.com");
		search.addBusinessNames("Le Bernardin");
		searches.add(search);

		try {
			// Create a new batch
			Batch batch = apiBatch.create();
			if (batch.isSuccess()) {
				// If batch was successfully created, ad jobs
				context.log("Created batch ID " + batch.getId());

				for (com.brightlocal.sdk.pojo.Search search1 : searches) {
					Job job = apiRanking.addSearchJob(batch, search1);
					if (job.isSuccess()) {
						context.log("Added job with ID " + job.getJobId());
					}
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
