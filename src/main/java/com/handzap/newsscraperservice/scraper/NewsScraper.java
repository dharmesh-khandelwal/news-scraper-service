package com.handzap.newsscraperservice.scraper;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.handzap.newsscraperservice.asynctask.NewsScraperTask;

/**
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
@Component
public class NewsScraper {

	@Autowired
	NewsScraperTask newsScraperTask;

	@PostConstruct
	public void init() {
		newsScraperTask.performScraping();
	}

}
