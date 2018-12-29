package com.handzap.newsscraper.init;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * News scraping initializer class to start scrapping process
 * 
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
@Component
public class NewsScraperInitializer {

	/**
	 * Instance of NewsScraperAsyncTask
	 */
	@Autowired
	private NewsScraperAsyncTask newsScraperAsyncTask;

	/**
	 * This method is executed automatically when spring dependency injection is
	 * complete. This function starts scrapping process in asynchronous way.
	 */
	@PostConstruct
	public void init() {
		newsScraperAsyncTask.performScraping();
	}

}
