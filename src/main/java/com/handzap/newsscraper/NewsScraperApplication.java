package com.handzap.newsscraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.handzap.newsscraper.init.NewsScraperInitializer;

/**
 * News Scraper Service spring boot application. Scraping process begins in
 * {@link NewsScraperInitializer}
 * 
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
@SpringBootApplication
public class NewsScraperApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsScraperApplication.class, args);
	}

}
