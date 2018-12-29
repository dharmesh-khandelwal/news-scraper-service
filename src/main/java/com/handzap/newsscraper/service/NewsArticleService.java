package com.handzap.newsscraper.service;

import com.handzap.newsscraper.dto.ArticleDTO;
import com.handzap.newsscraper.dto.ResponseDTO;
import com.handzap.newsscraper.entity.Author;
import com.handzap.newsscraper.exception.DtoEntityMappingException;

/**
 * NewsArticleService class with function to save and fetch articles
 * 
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
public interface NewsArticleService {

	/**
	 * Function to get articles whose author name contains given input
	 * 
	 * @param authorName
	 *            authorName
	 * @return dto with list of articles
	 * @throws DtoEntityMappingException
	 *             if mapping fails
	 */
	ResponseDTO<ArticleDTO> getArticlesByAuthor(String authorName) throws DtoEntityMappingException;

	/**
	 * Function to get articles whose title and description contains given input
	 * 
	 * @param title
	 *            title
	 * @param description
	 *            description
	 * @return dto with list of articles
	 * @throws DtoEntityMappingException
	 *             if mapping fails
	 */
	ResponseDTO<ArticleDTO> getArticlesByTitleAndDescription(String title, String description)
			throws DtoEntityMappingException;

	/**
	 * Function to save article
	 * 
	 * @param title title
	 * @param author author
	 * @param description description
	 */
	void saveArticle(String title, Author author, String description);

}
