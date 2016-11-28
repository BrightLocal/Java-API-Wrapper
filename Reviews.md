Reviews
-----

### Fetch Reviews (by profile URL)
```java
import com.brightlocal.sdk.ApiContext;
import com.brightlocal.sdk.api.ApiBatch;
import com.brightlocal.sdk.api.ApiReview;
import com.brightlocal.sdk.api.Batch;
import com.brightlocal.sdk.api.BatchResults;
import com.brightlocal.sdk.api.Job;
import com.brightlocal.sdk.exception.ApiException;
import com.brightlocal.sdk.pojo.Review;

ApiContext context = new ApiContext(API_KEY, API_SECRET);
// Initialize sdk
ApiBatch apiBatch = new ApiBatch(context);
ApiReview apiReview = new ApiReview(context);

// Prepare test reviews
List<Review> reviews = new ArrayList<>();
reviews.add(new Review("https://plus.google.com/114222978585544488148/about?hl=en", "USA"));
reviews.add(new Review("https://plus.google.com/117313296997732479889/about?hl=en", "USA"));
reviews.add(new Review("https://plus.google.com/111550668382222753542/about?hl=en", "USA"));

try {
	// Step 1: Create a new batch
	Batch batch = apiBatch.create();
	if (batch.isSuccess()) {
		// If batch was successfully created, ad jobs
		context.log("Created batch ID " + batch.getId());

		// Step 2: Add review lookup jobs to batch
		for (Review review : reviews) {
			Job job = apiReview.addFetchReviewsJob(batch, review);
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
```

### Fetch Reviews (by business data)
```java
import com.brightlocal.sdk.ApiContext;
import com.brightlocal.sdk.api.ApiBatch;
import com.brightlocal.sdk.api.ApiReview;
import com.brightlocal.sdk.api.Batch;
import com.brightlocal.sdk.api.BatchResults;
import com.brightlocal.sdk.api.Job;
import com.brightlocal.sdk.exception.ApiException;
import com.brightlocal.sdk.pojo.Review;

ApiContext context = new ApiContext(API_KEY, API_SECRET);
// Initialize sdk
ApiBatch apiBatch = new ApiBatch(context);
ApiReview apiReview = new ApiReview(context);

ReviewBusinessData reviewBusinessData = new ReviewBusinessData("Le Bernardin", "USA", "New York", "10019", "google", "(212) 554-1515");

try {
	// Step 1: Create a new batch
	Batch batch = apiBatch.create();
	if (batch.isSuccess()) {
		// If batch was successfully created, ad jobs
		context.log("Created batch ID " + batch.getId());

		// Step 2: Add review lookup jobs to batch
		Job job = apiReview.addFetchReviewsJob(batch, reviewBusinessData);
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
```
