package com.handzap.newsscraper.init;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
@Component
public class NewsScraperAsyncTask {

	@Value("${handzap.newsscraper.url-to-scrap}")
	private String newsUrl;

	@Autowired
	private NewsArticleService newsArticleService;

	private static final Executor EXCEUTOR = Executors.newCachedThreadPool();

	private static final Logger LOGGER = LoggerFactory.getLogger(NewsScraperAsyncTask.class);

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

		itemList.parallelStream().forEach(htmlAnchor -> completableFutures.add(CompletableFuture
				.runAsync(() -> getArticleDetails(htmlAnchor), EXCEUTOR).exceptionally(this::errorHandle)));

		CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()])).join();
		client.close();
		LOGGER.info("Scraping Complete.");
	}

	/**
	 * @param htmlAnchor
	 * @throws ScrappingException
	 * @throws IOException
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
			LOGGER.error("Error ocurred while scrapping article: {}", e.getMessage());
		}
		newsArticleService.saveArticle(title, author, description);
		LOGGER.info("Scraped article with title: {}", title);
	}

	/**
	 * @return
	 */
	private WebClient setupClient() {
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		return client;
	}

	private Void errorHandle(Throwable e) {
		if (e != null) {
			LOGGER.error("Error ocurred while scrapping: {}", e.getMessage());
		}
		return null;
	}
}
