package com.brightlocal.example.seo;

import com.brightlocal.sdk.ApiContext;
import com.brightlocal.sdk.api.ApiBatch;
import com.brightlocal.sdk.api.ApiOffsiteSeo;
import com.brightlocal.sdk.api.Batch;
import com.brightlocal.sdk.api.BatchResults;
import com.brightlocal.sdk.api.Job;
import com.brightlocal.sdk.exception.ApiException;
import com.brightlocal.sdk.pojo.SocialProfile;

/**
 * Example for showing SocialProfiles API call.
 */
public class SocialProfiles {

	public static final String API_KEY = "API_KEY_HERE";
	public static final String API_SECRET = "API_SECRET_HERE";

	public static void main(String[] args) {
		ApiContext context = new ApiContext(API_KEY, API_SECRET);
		context.enableDebug(true);

		// Initialize sdk
		ApiBatch apiBatch = new ApiBatch(context);
		ApiOffsiteSeo apiOffsiteSeo = new ApiOffsiteSeo(context);

		SocialProfile socialProfile = new SocialProfile("http://www.gramercytavern.com/", "Gramercy Tavern", "New York",
				"NY", "CAN");
		socialProfile.setFetchFacebook(SocialProfile.YES_NO.no);
		// socialProfile.setFetchFoursquare(SocialProfile.YES_NO.yes);
		// socialProfile.setFetchTwitter(SocialProfile.YES_NO.yes);
		socialProfile.setStreetAddress("42 E 20th St");
		socialProfile.setTelephone("(212) 477-0777");
		socialProfile.setPostcode("10003");

		try {
			// Step 1: Create a new batch
			Batch batch = apiBatch.create();
			if (batch.isSuccess()) {
				// If batch was successfully created, ad jobs
				context.log("Created batch ID " + batch.getId());

				// Step 2: Add social profile job to batch
				Job job = apiOffsiteSeo.socialProfiles(batch, socialProfile);
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
