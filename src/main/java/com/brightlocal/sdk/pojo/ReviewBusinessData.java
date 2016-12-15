package com.brightlocal.sdk.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Wrapper for Review Business Data requests.
 */
public class ReviewBusinessData {
	/**
	 * REQUIRED. A newline (\n) separated list of possible business names to
	 * search for. For example: The Rose Pub Rose Pub The Rose.
	 */
	@SerializedName("business-names")
	private String businessNames;
	/**
	 * REQUIRED. Determines whether or not to search against .com, .ca, co.uk,
	 * .com.au search engines. One of USA, CAN:EN, CAN:FR, GBR, AUS, IRL or NZL.
	 */
	@SerializedName("country")
	private String country;
	/**
	 * REQUIRED.
	 */
	@SerializedName("city")
	private String city;
	/**
	 * REQUIRED. A valid ZIP or postal code.
	 */
	@SerializedName("postcode")
	private String postcode;
	/**
	 * REQUIRED. See possible options in
	 * <a href="http://apidocs.brightlocal.com/#appendix">appendix</a>.
	 */
	@SerializedName("local-directory")
	private String localDirectory;
	/**
	 * 
	 */
	@SerializedName("telephone")
	private String telephone;
	/**
	 * 
	 */
	@SerializedName("street-address")
	private String streetAddress;
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
	 * @param businessNames
	 *            A newline (\n) separated list of possible business names to
	 *            search for. For example: The Rose Pub Rose Pub The Rose.
	 * @param country
	 *            Only USA.
	 * @param city
	 * @param postcode
	 *            A valid ZIP or postal code.
	 * @param localDirectory
	 *            See possible options in <a href=
	 *            "http://apidocs.brightlocal.com/#appendix">appendix</a>.
	 * @param telephone
	 */
	public ReviewBusinessData(String businessNames, String country, String city, String postcode,
			String localDirectory, String telephone) {
		super();
		this.businessNames = businessNames;
		this.country = country;
		this.city = city;
		this.postcode = postcode;
		this.localDirectory = localDirectory;
		this.telephone = telephone;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
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
