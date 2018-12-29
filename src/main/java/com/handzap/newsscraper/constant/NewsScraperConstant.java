package com.handzap.newsscraper.constant;

/**
 * String constant file for news scraping service
 * 
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
public class NewsScraperConstant {

	/**
	 * Private constructor for NewsScraperConstant
	 */
	private NewsScraperConstant() {
	}

	/**
	 * The constant UTC
	 */
	public static final String UTC = "UTC";
	/**
	 * The constant for default admin email
	 */
	public static final String DEFAULTADMIN = "defaultadmin@handzap.com";

	/**
	 * The constant NA
	 */
	public static final String NA = "NA";

	/**
	 * The constant for news description xpath
	 */
	public static final String XPATH_NEWS_DESCRIPTION = "//*[contains(@id, 'content-body')]";

	/**
	 * The constant for news author xpath
	 */
	public static final String XPATH_NEWS_AUTHOR = "//*[contains(@class, 'auth-nm')]";

	/**
	 * The constant for news title xpath
	 */
	public static final String XPATH_NEWS_TITLE = "//*[@class='title']";

	/**
	 * The constant for news list xpath
	 */
	public static final String XPATH_NEWS_LIST = "//*[@class='archive-list']/li/a";

}
