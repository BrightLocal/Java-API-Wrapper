package com.brightlocal.sdk;

import java.io.PrintStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Context needed to initialize API.
 */
public class ApiContext {
	/**
	 * API calls end point
	 */
	protected String endpoint;
	/**
	 * API version
	 */
	protected String version;
	/**
	 * API key
	 */
	protected String apiKey;
	/**
	 * API secret
	 */
	protected String apiSecret;
	/**
	 * Is debug enabled
	 */
	protected boolean isDebug = false;
	/**
	 * Current Logger
	 */
	protected PrintStream logger = System.out;

	/**
	 * Initialize API context
	 * 
	 * @param endpointBase
	 *            API calls end point
	 * @param version
	 *            API version
	 * @param apiKey
	 *            API key
	 * @param apiSecret
	 *            API secret
	 */
	public ApiContext(String endpointBase, String version, String apiKey, String apiSecret) {
		this.version = version;
		this.endpoint = endpointBase;
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
	}

	/**
	 * Initialize API context
	 * 
	 * @param apiKey
	 *            API key
	 * @param apiSecret
	 *            API secret
	 */
	public ApiContext(String apiKey, String appSecret) {
		this(ApiConfig.DEFAULT_ENDPOINT, ApiConfig.DEFAULT_API_VERSION, apiKey, appSecret);
	}

	/**
	 * Get API key
	 * 
	 * @return API key
	 */
	public String getApiKey() {
		return apiKey;
	}

	/**
	 * Get API secret
	 * 
	 * @return API secret
	 */
	public String getApiSecret() {
		return this.apiSecret;
	}

	/**
	 * Get API version
	 * 
	 * @return API version
	 */
	public String getVersion() {
		return this.version;
	}

	/**
	 * Get API calls end point
	 * 
	 * @return API calls end point
	 */
	public String getEndpoint() {
		return endpoint;
	}

	/**
	 * Is debug enabled
	 * 
	 * @return Is debug enabled
	 */
	public boolean isDebug() {
		return this.isDebug;
	}

	/**
	 * Enable debug
	 * 
	 * @param isDebug
	 *            true to enable, false to disable
	 * @return
	 */
	public ApiContext enableDebug(boolean isDebug) {
		this.isDebug = isDebug;
		return this;
	}

	/**
	 * Get current Logger
	 * 
	 * @return current Logger
	 */
	public PrintStream getLogger() {
		return this.logger;
	}

	/**
	 * Set current Logger
	 * 
	 * @param logger
	 * @return
	 */
	public ApiContext setLogger(PrintStream logger) {
		this.logger = logger;
		return this;
	}

	/**
	 * Log string
	 * 
	 * @param s
	 *            string
	 */
	public void log(String s) {
		if (isDebug && logger != null)
			logger.println(s);
	}

	/**
	 * Prepare signature
	 * 
	 * @return map "sig":signature; "expires":expires
	 */
	public Map<String, Object> getSigAndExpires() {
		String expires = "" + System.currentTimeMillis() + ApiConfig.MAX_EXPIRY;
		String sig = b64Encode(sha1(apiSecret, apiKey + expires));

		Map<String, Object> map = new HashMap<>();
		map.put("sig", sig);
		map.put("expires", expires);
		return map;
	}

	/**
	 * Hashing message with sha1, PHP hash_hmac("sha1", message, secret)
	 * 
	 * @param secret
	 * @param message
	 * @return string with hex bytes
	 */
	public static String sha1(String secret, String message) {
		try {
			// Get an hmac_sha1 key from the raw key bytes
			byte[] secretKey = secret.getBytes();
			SecretKeySpec signingKey = new SecretKeySpec(secretKey, "HmacSHA1");
			// Get an hmac_sha1 Mac instance and initialize with the signing key
			Mac sha1HMAC = Mac.getInstance("HmacSHA1");
			sha1HMAC.init(signingKey);

			byte[] bytes = sha1HMAC.doFinal(message.getBytes());

			// Covert array of Hex bytes to a String
			return toHex(bytes);
		} catch (Exception e) {
			throw new IllegalArgumentException("The provided app secret or access token is invalid!");
		}
	}

	/**
	 * Covert array of Hex bytes to a String
	 * 
	 * @param bytes
	 *            income bytes to convert to hex
	 * @return string with hex bytes
	 */
	public static String toHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%1$02x", b));
		}
		return sb.toString();
	}

	/**
	 * Encode string with Base64
	 * 
	 * @param in
	 *            input string
	 * @return encoded string
	 */
	public static String b64Encode(String in) {
		return new String(Base64.getEncoder().encode(in.getBytes()));
	}

	/**
	 * Decode string with Base64
	 * 
	 * @param in
	 *            input string
	 * @return decoded string
	 */
	public static String b64Decode(String in) {
		return new String(Base64.getDecoder().decode(in.getBytes()));
	}
}
