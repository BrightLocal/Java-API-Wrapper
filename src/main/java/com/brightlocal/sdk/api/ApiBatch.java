package com.brightlocal.sdk.api;

import com.brightlocal.sdk.ApiConfig;
import com.brightlocal.sdk.ApiContext;
import com.brightlocal.sdk.exception.ApiException;
import com.brightlocal.sdk.exception.ApiException.MalformedResponseException;
import com.brightlocal.sdk.network.ApiNode;
import com.brightlocal.sdk.network.ApiRequest;
import com.brightlocal.sdk.network.IResponseParser;

/**
 * A batch acts like a container for API requests. <br>
 * It allows you to group requests for several types of data together and poll
 * for results via a single consolidated call. <br>
 * All bar one of our raw data APIs need to be used within the context of a
 * batch. <br>
 * The basic steps for using a batch are outlined below:
 * <ul>
 * <ol>
 * 1. Request a new batch ID
 * </ol>
 * <ol>
 * 2. Add one or more requests for data to the batch
 * </ol>
 * <ol>
 * 3. Commit the batch
 * </ol>
 * <ol>
 * 4. Poll for results
 * </ol>
 * </ul>
 */
public class ApiBatch extends ApiNode {
	private static final String NODE_ID = "batch";

	public ApiBatch(ApiContext context) {
		setContext(context);
	}

	/**
	 * Creates a new batch. You can add jobs to the batch to enable you to fetch
	 * different kinds of data.
	 * <p>
	 * A batch can have one of 5 states:
	 * <ul>
	 * <li>Created - jobs can be added to the batch
	 * <li>Committed - batch closed for further jobs, no jobs being processed
	 * yet
	 * <li>Running - one or more jobs are being processed
	 * <li>Stopped - one or more jobs in the batch failed
	 * <li>Finished
	 * </ul>
	 * <p>
	 * Jobs within a batch can also have one of 5 states:
	 * <ul>
	 * <li>Pending - added to batch but not queued for processing
	 * <li>Queued - added to batch and queued for processing
	 * <li>Processing - currently being processed by a worker
	 * <li>Failed - processing failed
	 * <li>Completed - processing completed successfully and job results are
	 * available
	 * </ul>
	 * <p>
	 * Authentication for this method is via API key only. Whilst you can
	 * technically add as many jobs as you want to a batch we recommend you
	 * submit jobs in batches of a few hundred at a time. This will allow you to
	 * start receiving results sooner.
	 * 
	 * @return
	 * @throws ApiException
	 */
	public Batch create() throws ApiException {
		return create(false);
	}

	/**
	 * Creates a new batch. You can add jobs to the batch to enable you to fetch
	 * different kinds of data.
	 * <p>
	 * A batch can have one of 5 states:
	 * <ul>
	 * <li>Created - jobs can be added to the batch
	 * <li>Committed - batch closed for further jobs, no jobs being processed
	 * yet
	 * <li>Running - one or more jobs are being processed
	 * <li>Stopped - one or more jobs in the batch failed
	 * <li>Finished
	 * </ul>
	 * <p>
	 * Jobs within a batch can also have one of 5 states:
	 * <ul>
	 * <li>Pending - added to batch but not queued for processing
	 * <li>Queued - added to batch and queued for processing
	 * <li>Processing - currently being processed by a worker
	 * <li>Failed - processing failed
	 * <li>Completed - processing completed successfully and job results are
	 * available
	 * </ul>
	 * <p>
	 * Authentication for this method is via API key only. Whilst you can
	 * technically add as many jobs as you want to a batch we recommend you
	 * submit jobs in batches of a few hundred at a time. This will allow you to
	 * start receiving results sooner.
	 * 
	 * @param stopOnJobError
	 *            1 or 0. default 0. If errors are found in one job the batch
	 *            will be stopped and no further jobs will be processed.
	 * @return
	 * @throws ApiException
	 */
	public Batch create(boolean stopOnJobError) throws ApiException {
		Batch response = new ApiRequest<Batch>(context, NODE_ID, new IResponseParser<Batch>() {
			@Override
			public Batch parseResponse(String json, ApiContext context, ApiRequest<Batch> request)
					throws MalformedResponseException {
				return Batch.parseResponse(json, context, request);
			}
		}).setParam("stop-on-job-error", stopOnJobError ? "1" : "0").execute();

		return response;
	}

	/**
	 * Committing a batch signals that you’ve finished adding jobs. At this
	 * point our systems will start processing the jobs that you’ve added. <br>
	 * Once you commit a batch no further jobs can be added to it.
	 * 
	 * @param batch
	 * @return
	 * @throws ApiException
	 */
	public Batch commit(Batch batch) throws ApiException {
		Batch response = new ApiRequest<Batch>(context, NODE_ID, ApiConfig.HTTP_METHOD_PUT,
				new IResponseParser<Batch>() {
					@Override
					public Batch parseResponse(String json, ApiContext context, ApiRequest<Batch> request)
							throws MalformedResponseException {
						return Batch.parseResponse(json, context, request);
					}
				}).setParam("batch-id", "" + batch.getId()).execute();

		response.id = batch.getId();

		return response;
	}

	/**
	 * This retrieves the results of all jobs added to the batch. <br>
	 * Results are added as they’re returned so you can keep polling this
	 * endpoint to retrieve results progressively.<br>
	 * Once results for all jobs have been returned the batch will be marked as
	 * “Finished”.
	 * <p>
	 * Authentication for this method is via API key only.
	 * 
	 * @param batch
	 * @return
	 * @throws ApiException
	 */
	public BatchResults getResults(Batch batch) throws ApiException {
		BatchResults response = new ApiRequest<BatchResults>(context, NODE_ID, ApiConfig.HTTP_METHOD_GET,
				new IResponseParser<BatchResults>() {
					@Override
					public BatchResults parseResponse(String json, ApiContext context, ApiRequest<BatchResults> request)
							throws MalformedResponseException {
						return BatchResults.parseResponse(json, context, request);
					}
				}).setParam("batch-id", "" + batch.getId()).execute();

		return response;
	}

	/**
	 * Delete a batch. This also deletes all data retrieved for jobs added to
	 * the batch.
	 * <p>
	 * Authentication for this method is via API key only.
	 * 
	 * @param batch
	 * @return
	 * @throws ApiException
	 */
	public Batch delete(Batch batch) throws ApiException {
		Batch response = new ApiRequest<Batch>(context, NODE_ID, ApiConfig.HTTP_METHOD_DELETE,
				new IResponseParser<Batch>() {
					@Override
					public Batch parseResponse(String json, ApiContext context, ApiRequest<Batch> request)
							throws MalformedResponseException {
						return Batch.parseResponse(json, context, request);
					}
				}).setParam("batch-id", "" + batch.getId()).execute();

		response.id = batch.getId();

		return response;
	}

	/**
	 * Cancels a batch midway through processing. <br>
	 * Any jobs in the batch that haven’t already been processed will also be
	 * cancelled.
	 * 
	 * @param batch
	 * @return
	 * @throws ApiException
	 */
	public Batch stop(Batch batch) throws ApiException {
		Batch response = new ApiRequest<Batch>(context, NODE_ID, ApiConfig.HTTP_METHOD_PUT,
				new IResponseParser<Batch>() {
					@Override
					public Batch parseResponse(String json, ApiContext context, ApiRequest<Batch> request)
							throws MalformedResponseException {
						return Batch.parseResponse(json, context, request);
					}
				}).setParam("batch-id", "" + batch.getId()).execute();

		response.id = batch.getId();

		return response;
	}
}
