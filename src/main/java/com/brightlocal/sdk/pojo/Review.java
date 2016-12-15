package com.brightlocal.sdk.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Wrapper for Review requests.
 */
public class Review {
	/**
	 * REQUIRED. For requests to fetch Google reviews please see <a href=
	 * "http://apidocs.brightlocal.com/#constructing-google+-urls">constructing
	 * Google URLs</a>.
	 */
	@SerializedName("profile-url")
	private String profileUrl;
	/**
	 * REQUIRED. Determines whether or not to search against .com, .ca, co.uk,
	 * .com.au search engines. One of USA, CAN:EN, CAN:FR, GBR, AUS, IRL or NZL.
	 */
	@SerializedName("country")
	private String country;
	/**
	 * ‘rating’ or 'date’. By default 'date’.
	 */
	@SerializedName("sort")
	private String sort;
	/**
	 * Positive number or 'all’. By default '250'.
	 */
	@SerializedName("reviews-limit")
	private String reviewsLimit;
	/**
	 * Date Format: Y-m-d or Y-m-d H:i:s. By default not specified.
	 */
	@SerializedName("date-from")
	private String dateFrom;

	/**
	 * Constructor with required fields
	 * 
	 * @param profileUrl
	 *            For requests to fetch Google reviews please see <a href=
	 *            "http://apidocs.brightlocal.com/#constructing-google+-urls">constructing
	 *            Google URLs</a>.
	 * @param country
	 *            Determines whether or not to search against .com, .ca, co.uk,
	 *            .com.au search engines. One of USA, CAN:EN, CAN:FR, GBR, AUS,
	 *            IRL or NZL.
	 */
	public Review(String profileUrl, String country) {
		super();
		this.profileUrl = profileUrl;
		this.country = country;
	}

	/**
	 * ‘rating’ or 'date’. By default 'date’.
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * Positive number or 'all’. By default '250'.
	 */
	public void setReviewsLimit(String reviewsLimit) {
		this.reviewsLimit = reviewsLimit;
	}

	/**
	 * Date Format: Y-m-d or Y-m-d H:i:s. By default not specified.
	 */
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
}
