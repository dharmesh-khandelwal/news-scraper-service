/**
 * 
 */
package com.handzap.newsscraper.service;

import com.handzap.newsscraper.entity.Author;

/**
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
public interface NewsArticleService {

	/**
	 * @param title
	 * @param author
	 * @param description
	 */
	void saveArticle(String title, Author author, String description);

}
