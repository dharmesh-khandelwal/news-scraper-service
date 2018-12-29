package com.handzap.newsscraper.init;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.handzap.newsscraper.constant.NewsScraperConstant;
import com.handzap.newsscraper.entity.Author;
import com.handzap.newsscraper.service.NewsArticleService;

/**
 * This class contains method to scrap news article asynchronously
 * 
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
@Component
public class NewsScraperAsyncTask {

	/**
	 * The url from which to scrap articles
	 */
	@Value("${handzap.newsscraper.url-to-scrap}")
	private String newsUrl;

	/**
	 * Instance for NewsArticleService
	 */
	@Autowired
	private NewsArticleService newsArticleService;

	private static final Logger LOGGER = LoggerFactory.getLogger(NewsScraperAsyncTask.class);

	/**
	 * Async function which first get the main page, then get list of articles on
	 * that page. Then for each article, this function go to the respective article
	 * page and get title, description and author name
	 */
	@Async
	public void performScraping() {
		LOGGER.info("{} running in {}", getClass().getSimpleName(), Thread.currentThread());
		LOGGER.info("Started scraping...");
		List<HtmlAnchor> itemList = new ArrayList<>();
		List<CompletableFuture<Void>> completableFutures = new ArrayList<>();
		HtmlPage page;
		WebClient client = setupClient();
		try {
			page = client.getPage(newsUrl);
			itemList = page.getByXPath(NewsScraperConstant.XPATH_NEWS_LIST);
		} catch (FailingHttpStatusCodeException | IOException e) {
			LOGGER.error("Error ocurred while getting page: {}", e.getMessage());
		}

		itemList.parallelStream().forEach(htmlAnchor -> completableFutures
				.add(CompletableFuture.runAsync(() -> getArticleDetails(htmlAnchor)).exceptionally(this::errorHandle)));

		CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()])).join();
		client.close();
		LOGGER.info("Scraping Complete.");
	}

	/**
	 * This helper function fetches individual article page, create article entity
	 * and saves the article in db
	 * 
	 * @param htmlAnchor
	 *            htmlAnchor
	 */
	private void getArticleDetails(HtmlAnchor htmlAnchor) {
		String title = null;
		Author author = null;
		String description = null;
		try {
			HtmlPage postPage = htmlAnchor.click();
			HtmlElement titleElement = postPage.getFirstByXPath(NewsScraperConstant.XPATH_NEWS_TITLE);
			title = titleElement.asText();
			HtmlAnchor authorAnchor = postPage.getFirstByXPath(NewsScraperConstant.XPATH_NEWS_AUTHOR);
			author = new Author(authorAnchor == null ? NewsScraperConstant.NA : authorAnchor.asText());
			HtmlElement descriptionElement = postPage.getFirstByXPath(NewsScraperConstant.XPATH_NEWS_DESCRIPTION);
			description = descriptionElement == null ? NewsScraperConstant.NA : descriptionElement.asText();
		} catch (IOException e) {
			LOGGER.error("Error ocurred while scraping article: {}", e.getMessage());
		}
		newsArticleService.saveArticle(title, author, description);
		LOGGER.info("Scraped article with title: {}", title);
	}

	/**
	 * This function sets up the client
	 * 
	 * @return webclient
	 */
	private WebClient setupClient() {
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		return client;
	}

	/**
	 * Helper function for error handling
	 * 
	 * @param e
	 *            exception
	 * @return void
	 */
	private Void errorHandle(Throwable e) {
		if (e != null) {
			LOGGER.error("Error ocurred while scraping: {}", e.getMessage());
		}
		return null;
	}
}
