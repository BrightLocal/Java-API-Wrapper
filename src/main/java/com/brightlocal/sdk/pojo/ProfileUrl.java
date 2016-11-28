package com.brightlocal.sdk.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Wrapper for Fetch Profile Url requests.
 */
public class ProfileUrl {
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
	 * REQUIRED. See possible options in appendix below.
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
	 * Constructor with required fields
	 * 
	 * @param businessNames
	 *            A newline (\n) separated list of possible business names to
	 *            search for. For example: The Rose Pub Rose Pub The Rose.
	 * @param country
	 *            Determines whether or not to search against .com, .ca, co.uk,
	 *            .com.au search engines. One of USA, CAN:EN, CAN:FR, GBR, AUS,
	 *            IRL or NZL.
	 * @param city
	 *            city
	 * @param postcode
	 *            A valid ZIP or postal code.
	 * @param localDirectory
	 */
	public ProfileUrl(String businessNames, String country, String city, String postcode, String localDirectory) {
		super();
		this.businessNames = businessNames;
		this.country = country;
		this.city = city;
		this.postcode = postcode;
		this.localDirectory = localDirectory;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
}
