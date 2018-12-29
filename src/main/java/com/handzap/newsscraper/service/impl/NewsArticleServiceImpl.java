/**
 * 
 */
package com.handzap.newsscraper.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.handzap.newsscraper.entity.Article;
import com.handzap.newsscraper.entity.Author;
import com.handzap.newsscraper.repository.ArticleRepository;
import com.handzap.newsscraper.repository.AuthorRepository;
import com.handzap.newsscraper.service.NewsArticleService;
import com.handzap.newsscraper.util.MetaDataUtil;

/**
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
@Service
@Transactional
public class NewsArticleServiceImpl implements NewsArticleService {

	@Autowired
	MetaDataUtil newsScraperUtil;

	@Autowired
	ArticleRepository articleRepository;

	@Autowired
	AuthorRepository authorRepository;

	/**
	 * @param title
	 * @param author
	 * @param description
	 */
	@Override
	public void saveArticle(String title, Author author, String description) {
		Optional<Author> fetchedAuthor = authorRepository.findByAuthorName(author.getAuthorName());
		if (fetchedAuthor.isPresent()) {
			author = fetchedAuthor.get();
		} else {
			authorRepository.save(newsScraperUtil.setMetaData(author));
		}

		articleRepository.save(newsScraperUtil.setMetaData(new Article(author, title, description)));
	}

}
