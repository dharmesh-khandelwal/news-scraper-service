package com.handzap.newsscraperservice.asynctask;

import java.io.IOException;
import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
@Component
public class NewsScraperTask {

	@Async
	public void performScraping() {
		final String message = String.format("%s running in %s", getClass().getSimpleName(), Thread.currentThread());
		System.out.println(message);

		String baseUrl = "https://www.thehindu.com/archive/web/2018/12/27/";
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		try {
			HtmlPage page = client.getPage(baseUrl);
			List<HtmlAnchor> itemList = page.getByXPath("//*[@class='archive-list']/li/a");
			if (itemList.isEmpty()) {
				System.out.println("No item found");
			} else {
				for (HtmlAnchor htmlAnchor : itemList) {
					HtmlPage postPage = htmlAnchor.click();
					HtmlElement title = postPage.getFirstByXPath("//*[@class='title']");
					System.out.println(title.asText());
					HtmlAnchor author = postPage.getFirstByXPath("//*[contains(@class, 'auth-nm')]");
					if (author == null) {
						System.out.println("NO_AUTHOR");
					} else {
						System.out.println(author.asText());
					}
					HtmlElement description = postPage.getFirstByXPath("//*[contains(@id, 'content-body')]");
					if (description == null) {
						System.out.println("NO_DESCRIPTION");
					} else {
						System.out.println(description.asText());
					}

				}
			}
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		} finally {
			client.close();
		}

	}

}
