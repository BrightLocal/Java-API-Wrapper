Batches
-----

### Create Batch
```java
import com.brightlocal.sdk.ApiContext;
import com.brightlocal.sdk.api.ApiBatch;
import com.brightlocal.sdk.api.Batch;
import com.brightlocal.sdk.exception.ApiException;

ApiContext context = new ApiContext(API_KEY, API_SECRET);
// Initialize sdk
ApiBatch apiBatch = new ApiBatch(context);
try {
	// Create a new batch
	Batch batch = apiBatch.create();
	if (batch.isSuccess()) {
		int batchId = batch.getId();
	}
} catch (ApiException e) {
	e.printStackTrace();
}

### Commit Batch
```java
import com.brightlocal.sdk.ApiContext;
import com.brightlocal.sdk.api.ApiBatch;
import com.brightlocal.sdk.api.Batch;
import com.brightlocal.sdk.exception.ApiException;

ApiContext context = new ApiContext(API_KEY, API_SECRET);
// Initialize sdk
ApiBatch apiBatch = new ApiBatch(context);
try {
	// Create a new batch
	Batch batch = apiBatch.create();
	if (batch.isSuccess()) {
		batch = apiBatch.commit(batch);
		if (batch.isSuccess()) {
			context.log("Committed batch successfully.");
		}
	}
} catch (ApiException e) {
	e.printStackTrace();
}
```

### Get Results
```java
import com.brightlocal.sdk.ApiContext;
import com.brightlocal.sdk.api.ApiBatch;
import com.brightlocal.sdk.api.Batch;
import com.brightlocal.sdk.api.BatchResults;
import com.brightlocal.sdk.exception.ApiException;

ApiContext context = new ApiContext(API_KEY, API_SECRET);
// Initialize sdk
ApiBatch apiBatch = new ApiBatch(context);

try {
	// Create a new batch
	Batch batch = apiBatch.create();
	if (batch.isSuccess()) {
		// If batch was successfully created, request batch results
		BatchResults batchResults = apiBatch.getResults(batch);
		if (batchResults.isSuccess()) {
			// process batch results here
		}
	}
} catch (ApiException e) {
	e.printStackTrace();
}
```

### Delete
```java
import com.brightlocal.sdk.ApiContext;
import com.brightlocal.sdk.api.ApiBatch;
import com.brightlocal.sdk.api.Batch;
import com.brightlocal.sdk.exception.ApiException;

ApiContext context = new ApiContext(API_KEY, API_SECRET);
// Initialize sdk
ApiBatch apiBatch = new ApiBatch(context);
try {
	// Create a new batch
	Batch batch = apiBatch.create();
	if (batch.isSuccess()) {
		batch = apiBatch.delete(batch);
		if (batch.isSuccess()) {
			context.log("Deleted batch successfully.");
		}
	}
} catch (ApiException e) {
	e.printStackTrace();
}
```