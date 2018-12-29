/**
 * 
 */
package com.handzap.newsscraper.service;

import com.handzap.newsscraper.dto.ArticleDTO;
import com.handzap.newsscraper.dto.ResponseDTO;
import com.handzap.newsscraper.entity.Author;

/**
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
public interface NewsArticleService {

	/**
	 * @param authorName
	 * @return
	 */
	ResponseDTO<ArticleDTO> getArticlesByAuthor(String authorName);

	/**
	 * @param title
	 * @param description
	 * @return
	 */
	ResponseDTO<ArticleDTO> getArticlesByTitleAndDescription(String title, String description);

	/**
	 * @param title
	 * @param author
	 * @param description
	 */
	void saveArticle(String title, Author author, String description);

}
