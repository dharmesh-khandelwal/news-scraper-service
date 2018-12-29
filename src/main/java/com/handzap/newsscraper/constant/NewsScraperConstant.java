/**
 * 
 */
package com.handzap.newsscraper.constant;

/**
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
public class NewsScraperConstant {

	private NewsScraperConstant() {
	}

	/**
	 * 
	 */
	public static final String UTC = "UTC";
	/**
	 * 
	 */
	public static final String DEFAULTADMIN = "defaultadmin@handzap.com";

	/**
	 * 
	 */
	public static final String NA = "NA";

	/**
	 * 
	 */
	public static final String XPATH_NEWS_DESCRIPTION = "//*[contains(@id, 'content-body')]";

	/**
	 * 
	 */
	public static final String XPATH_NEWS_AUTHOR = "//*[contains(@class, 'auth-nm')]";

	/**
	 * 
	 */
	public static final String XPATH_NEWS_TITLE = "//*[@class='title']";

	/**
	 * 
	 */
	public static final String XPATH_NEWS_LIST = "//*[@class='archive-list']/li/a";

}
