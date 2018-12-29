package com.handzap.newsscraper.scraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.handzap.newsscraper.entity.Author;
import com.handzap.newsscraper.service.NewsArticleService;

/**
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
@Component
public class NewsScraperAsyncTask {

	@Autowired
	NewsArticleService newsArticleService;

	private static final Executor EXCEUTOR = Executors.newCachedThreadPool();

	@Async
	public void performScraping() {
		System.out.println(String.format("%s running in %s", getClass().getSimpleName(), Thread.currentThread()));

		List<HtmlAnchor> itemList = new ArrayList<>();
		List<CompletableFuture<Void>> completableFutures = new ArrayList<>();
		HtmlPage page;

		String baseUrl = "https://www.thehindu.com/archive/web/2018/12/27/";
		WebClient client = setupClient();

		try {
			page = client.getPage(baseUrl);
			itemList = page.getByXPath("//*[@class='archive-list']/li/a");
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}

		itemList.parallelStream().forEach(htmlAnchor -> completableFutures.add(CompletableFuture
				.runAsync(() -> getArticleDetails(htmlAnchor), EXCEUTOR).exceptionally(this::errorHandle)));

		CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()])).join();
		client.close();
	}

	/**
	 * @param htmlAnchor
	 * @throws IOException
	 */
	private void getArticleDetails(HtmlAnchor htmlAnchor) {
		System.out.println(String.format("%s running in %s", getClass().getSimpleName(), Thread.currentThread()));

		String title = null;
		Author author = null;
		String description = null;
		try {
			HtmlPage postPage = htmlAnchor.click();
			HtmlElement titleElement = postPage.getFirstByXPath("//*[@class='title']");
			title = titleElement.asText();

			HtmlAnchor authorAnchor = postPage.getFirstByXPath("//*[contains(@class, 'auth-nm')]");
			author = new Author(authorAnchor == null ? "NA" : authorAnchor.asText());

			HtmlElement descriptionElement = postPage.getFirstByXPath("//*[contains(@id, 'content-body')]");
			description = descriptionElement == null ? "NA" : descriptionElement.asText();
		} catch (IOException e) {
			e.printStackTrace();
		}
		newsArticleService.saveArticle(title, author, description);
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
			e.printStackTrace();

		}
		return null;
	}

}
