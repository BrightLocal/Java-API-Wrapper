package com.brightlocal.sdk.pojo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
/**
 * Wrapper for Search requests. 
 */
public class Search {
	public static enum YES_NO{
		yes, no
	}
	/**
	 * REQUIRED. One of google, google-mobile, google-local, yahoo, yahoo-local, bing, bing-local.
	 */
	@SerializedName("search-engine")
	String searchEngine;
	/**
	 * REQUIRED. Determines whether or not to search against .com, .ca, co.uk, .com.au search engines. One of USA, CAN:EN, CAN:FR, GBR, AUS, IRL or NZL.
	 */
	@SerializedName("country")
	String country;
	/**
	 * Allows you to optionally localize results by specifying your physical location. Specify a ZIP, city name or region.<br>Only applicable to US searches.
	 */
	@SerializedName("google-location")
	String googleLocation;
	/**
	 * Allows you to optionally localize results by specifying your physical location. 
	 */
	@SerializedName("bing-location")
	String bingLocation;
	/**
	 * REQUIRED. The search term to get ranking information for.
	 */
	@SerializedName("search-term")
	String searchTerm;
	/**
	 * The URLs to get ranking information for. Encode as a JSON string, e.g. [“www.bluehillfarm.com”, “www.candle79.com”, “shabutatsu.com”, “marea-nyc.com”, “www.taorestaurant.com”] (max 10).
	 */
	@SerializedName("urls")
	List<String> urls = new ArrayList<>();
	/**
	 * A list of possible business names to search for. Encode as a JSON string, e.g. [“The Rose Pub”,“Rose Pub”,“The Rose”]. <br>For backwards compatibility this also supports a newline (\n) separated list.
	 */
	@SerializedName("business-names")
	List<String> businessNames = new ArrayList<>();
	/**
	 * A valid ZIP or country postal code.
	 */
	@SerializedName("postcode")
	String postcode;
	/**
	 * A valid telephone number.
	 */
	@SerializedName("telephone")
	String telephone;
	/**
	 * Determines whether or not to include results matched by name, telephone and/or ZIP/postal code. <br>One of yes or no. <br>This should be used in conjunction with the postal and telephone parameters.
	 */
	@SerializedName("include-secondary-matches")
	YES_NO includeSecondaryMatches;
	/**
	 * Include details of all SERPs returned, not just the matches. <br>Accepts “yes” or “no”. The default is “no”.
	 */
	@SerializedName("listings")
	YES_NO listings;
	/**
	 * Determines whether or not to generate SERP screenshots and include the links to those screenshots in the response. <br>Accepts “yes” or “no”. The default is “no”.
	 */
	@SerializedName("screenshots")
	YES_NO screenshots;
	
	/**
	 * Constructor with required fields
	 * @param searchEngine
	 * @param country
	 * @param searchTerm
	 */
	public Search(String searchEngine, String country, String searchTerm) {
		super();
		this.searchEngine = searchEngine;
		this.country = country;
		this.searchTerm = searchTerm;
	}

	/**
	 * Allows you to optionally localize results by specifying your physical location. Specify a ZIP, city name or region.<br>Only applicable to US searches.
	 */
	public void setGoogleLocation(String googleLocation) {
		this.googleLocation = googleLocation;
	}

	/**
	 * Allows you to optionally localize results by specifying your physical location. 
	 */
	public void setBingLocation(String bingLocation) {
		this.bingLocation = bingLocation;
	}

	/**
	 * The URLs to get ranking information for. Encode as a JSON string, e.g. [“www.bluehillfarm.com”, “www.candle79.com”, “shabutatsu.com”, “marea-nyc.com”, “www.taorestaurant.com”] (max 10).
	 */
	public void setUrls(List<String> urls) {
		this.urls = urls;
	}
	/**
	 * Add url into urls list
	 * @param url
	 */
	public void addUrl(String url) {
		if(urls != null)
			urls.add(url);
	}
	/**
	 * A list of possible business names to search for. Encode as a JSON string, e.g. [“The Rose Pub”,“Rose Pub”,“The Rose”]. <br>For backwards compatibility this also supports a newline (\n) separated list.
	 */
	public void setBusinessNames(List<String> businessNames) {
		this.businessNames = businessNames;
	}
	/**
	 * Add business name into list
	 * @param businessName
	 */
	public void addBusinessNames(String businessName) {
		if(businessNames != null)
			businessNames.add(businessName);
	}

	/**
	 * A valid ZIP or country postal code.
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * A valid telephone number.
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * Determines whether or not to include results matched by name, telephone and/or ZIP/postal code. <br>One of yes or no. <br>This should be used in conjunction with the postal and telephone parameters.
	 */
	public void setIncludeSecondaryMatches(YES_NO includeSecondaryMatches) {
		this.includeSecondaryMatches = includeSecondaryMatches;
	}

	/**
	 * Include details of all SERPs returned, not just the matches. <br>Accepts “yes” or “no”. The default is “no”.
	 */
	public void setListings(YES_NO listings) {
		this.listings = listings;
	}

	/**
	 * Determines whether or not to generate SERP screenshots and include the links to those screenshots in the response. <br>Accepts “yes” or “no”. The default is “no”.
	 */
	public void setScreenshots(YES_NO screenshots) {
		this.screenshots = screenshots;
	}

	public String getSearchEngine() {
		return searchEngine;
	}

	public String getCountry() {
		return country;
	}

	public String getGoogleLocation() {
		return googleLocation;
	}

	public String getBingLocation() {
		return bingLocation;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public List<String> getUrls() {
		return urls;
	}

	public List<String> getBusinessNames() {
		return businessNames;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getTelephone() {
		return telephone;
	}

	public YES_NO getIncludeSecondaryMatches() {
		return includeSecondaryMatches;
	}

	public YES_NO getListings() {
		return listings;
	}

	public YES_NO getScreenshots() {
		return screenshots;
	}
}
