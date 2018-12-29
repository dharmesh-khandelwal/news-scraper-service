/**
 * 
 */
package com.handzap.newsscraper.service;

import com.handzap.newsscraper.dto.ArticleDTO;
import com.handzap.newsscraper.dto.ResponseDTO;
import com.handzap.newsscraper.entity.Author;
import com.handzap.newsscraper.exception.DtoEntityMappingException;

/**
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
public interface NewsArticleService {

	/**
	 * @param authorName
	 * @return
	 * @throws DtoEntityMappingException 
	 */
	ResponseDTO<ArticleDTO> getArticlesByAuthor(String authorName) throws DtoEntityMappingException;

	/**
	 * @param title
	 * @param description
	 * @return
	 * @throws DtoEntityMappingException 
	 */
	ResponseDTO<ArticleDTO> getArticlesByTitleAndDescription(String title, String description) throws DtoEntityMappingException;

	/**
	 * @param title
	 * @param author
	 * @param description
	 */
	void saveArticle(String title, Author author, String description);

}
