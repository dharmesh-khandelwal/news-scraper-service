package com.handzap.newsscraper.scraper;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
@Component
public class NewsScraperInitializer {

	@Autowired
	NewsScraperAsyncTask newsScraperAsyncTask;

	@PostConstruct
	public void init() {
		newsScraperAsyncTask.performScraping();
	}

}
