package com.brightlocal.sdk.pojo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Wrapper for Social Profile requests.
 */
public class SocialProfile {
	public static enum YES_NO {
		yes, no
	}

	/**
	 * REQUIRED. URL of the business website. Can be specified with or without
	 * http(s).
	 */
	@SerializedName("website-url")
	private String websiteUrl;
	/**
	 * yes or no. Defaults to no. Fetch Twitter profile for the business if
	 * available.
	 */
	@SerializedName("fetch-twitter")
	private YES_NO fetchTwitter;
	/**
	 * yes or no. Defaults to no. Fetch Facebook profile for the business if
	 * available.
	 */
	@SerializedName("fetch-facebook")
	private YES_NO fetchFacebook;
	/**
	 * yes or no. Defaults to no. Fetch Foursquare profile for the business if
	 * available.
	 */
	@SerializedName("fetch-foursquare")
	private YES_NO fetchFoursquare;
	/**
	 * REQUIRED. A list of business names, e.g. [“Delmonico’s”,“Delmonico’s
	 * Restaurant”]
	 */
	@SerializedName("business-names")
	private List<String> businessNames;
	/**
	 * 
	 */
	@SerializedName("street-address")
	private String streetAddress;
	/**
	 * REQUIRED.
	 */
	@SerializedName("city")
	private String city;
	/**
	 * REQUIRED. (USA only). A valid two letter state code, e.g. CA.
	 */
	@SerializedName("state-code")
	private String stateCode;
	/**
	 * 
	 */
	@SerializedName("telephone")
	private String telephone;
	/**
	 * REQUIRED. A valid ZIP or postal code.
	 */
	@SerializedName("postcode")
	private String postcode;
	/**
	 * REQUIRED. Valid 3 letter ISO code. e.g, USA, GBR, CAN.
	 */
	@SerializedName("country")
	private String country;
	/**
	 * This determines how our crawler extracts information from your website.
	 * <br>
	 * We crawl your website to help us identify Twitter, Facebook and
	 * Foursquare profile information.
	 * <ul>
	 * <ol>
	 * 1. Only follow links that lead to the same root domain (e.g.
	 * foo.brightlocal.com and bar.brightlocal.com).
	 * </ol>
	 * <ol>
	 * 2. Only follow links that lead to the same domain (e.g.
	 * www.brightlocal.com). This is the default.
	 * </ol>
	 * <ol>
	 * 3. Only follow links that lead to pages under the same path as that
	 * specified in website-url.
	 * </ol>
	 * </ul>
	 */
	@SerializedName("follow-mode")
	private String followMode;

	/**
	 * Constructor with required fields
	 * 
	 * @param websiteUrl
	 *            URL of the business website. Can be specified with or without
	 *            http(s).
	 * @param businessNames
	 *            A list of business names, e.g. [“Delmonico’s”,“Delmonico’s
	 *            Restaurant”]
	 * @param city
	 * @param stateCode
	 *            USA only). A valid two letter state code, e.g. CA.
	 * @param country
	 *            Valid 3 letter ISO code. e.g, USA, GBR, CAN.
	 */
	public SocialProfile(String websiteUrl, List<String> businessNames, String city, String stateCode, String country) {
		super();
		this.websiteUrl = websiteUrl;
		this.businessNames = businessNames;
		this.city = city;
		this.stateCode = stateCode;
		this.country = country;
	}

	/**
	 * Constructor with required fields
	 * 
	 * @param websiteUrl
	 * @param businessName
	 * @param city
	 * @param stateCode
	 * @param country
	 */
	public SocialProfile(String websiteUrl, String businessName, String city, String stateCode, String country) {
		super();
		this.websiteUrl = websiteUrl;
		this.businessNames = new ArrayList<>();
		this.businessNames.add(businessName);
		this.city = city;
		this.stateCode = stateCode;
		this.country = country;
	}

	/**
	 * yes or no. Defaults to no. Fetch Twitter profile for the business if
	 * available.
	 * 
	 * @param fetchTwitter
	 */
	public void setFetchTwitter(YES_NO fetchTwitter) {
		this.fetchTwitter = fetchTwitter;
	}

	/**
	 * yes or no. Defaults to no. Fetch Facebook profile for the business if
	 * available.
	 * 
	 * @param fetchFacebook
	 */
	public void setFetchFacebook(YES_NO fetchFacebook) {
		this.fetchFacebook = fetchFacebook;
	}

	/**
	 * yes or no. Defaults to no. Fetch Foursquare profile for the business if
	 * available.
	 * 
	 * @param fetchFoursquare
	 */
	public void setFetchFoursquare(YES_NO fetchFoursquare) {
		this.fetchFoursquare = fetchFoursquare;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * A valid ZIP or postal code.
	 * 
	 * @param postcode
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * This determines how our crawler extracts information from your website.
	 * <br>
	 * We crawl your website to help us identify Twitter, Facebook and
	 * Foursquare profile information.
	 * <ul>
	 * <ol>
	 * 1. Only follow links that lead to the same root domain (e.g.
	 * foo.brightlocal.com and bar.brightlocal.com).
	 * </ol>
	 * <ol>
	 * 2. Only follow links that lead to the same domain (e.g.
	 * www.brightlocal.com). This is the default.
	 * </ol>
	 * <ol>
	 * 3. Only follow links that lead to pages under the same path as that
	 * specified in website-url.
	 * </ol>
	 * </ul>
	 */
	public void setFollowMode(String followMode) {
		this.followMode = followMode;
	}
}
