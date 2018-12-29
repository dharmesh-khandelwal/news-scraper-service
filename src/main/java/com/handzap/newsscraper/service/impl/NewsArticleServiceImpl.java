/**
 * 
 */
package com.handzap.newsscraper.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ConfigurationException;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.handzap.newsscraper.dto.ArticleDTO;
import com.handzap.newsscraper.dto.ResponseDTO;
import com.handzap.newsscraper.entity.Article;
import com.handzap.newsscraper.entity.Author;
import com.handzap.newsscraper.exception.DtoEntityMappingException;
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
	private MetaDataUtil metaDataUtil;

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private ModelMapper modelMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.handzap.newsscraper.service.NewsArticleService#getArticlesByAuthor(java.
	 * lang.String)
	 */
	@Override
	public ResponseDTO<ArticleDTO> getArticlesByAuthor(String authorName) throws DtoEntityMappingException {
		Set<Article> fetchedArticles = new HashSet<>();
		ResponseDTO<ArticleDTO> authorResponseDTO = new ResponseDTO<>();
		List<Author> fetchedAuthors = authorRepository.findByAuthorNameIgnoreCaseContaining(authorName);

		fetchedAuthors.parallelStream().forEach(author -> fetchedArticles.addAll(author.getArticles()));

		List<ArticleDTO> articles = null;
		try {
			articles = modelMapper.map(fetchedArticles, new TypeToken<List<ArticleDTO>>() {
			}.getType());
		} catch (IllegalArgumentException | ConfigurationException | MappingException exception) {
			throw new DtoEntityMappingException(exception);
		}
		authorResponseDTO.setResponse(articles);
		return authorResponseDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.handzap.newsscraper.service.NewsArticleService#
	 * getArticlesByTitleAndDescription(java.lang.String, java.lang.String)
	 */
	@Override
	public ResponseDTO<ArticleDTO> getArticlesByTitleAndDescription(String title, String description) throws DtoEntityMappingException {
		ResponseDTO<ArticleDTO> authorResponseDTO = new ResponseDTO<>();
		List<Article> fetchedArticles = articleRepository
				.findArticlesByTitleIgnoreCaseContainingAndDescriptionIgnoreCaseContaining(title, description);
		List<ArticleDTO> articles = null;
		try {
			articles = modelMapper.map(fetchedArticles, new TypeToken<List<ArticleDTO>>() {
			}.getType());
		} catch (IllegalArgumentException | ConfigurationException | MappingException exception) {
			throw new DtoEntityMappingException(exception);
		}
		authorResponseDTO.setResponse(articles);
		return authorResponseDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.handzap.newsscraper.service.NewsArticleService#saveArticle(java.lang.
	 * String, com.handzap.newsscraper.entity.Author, java.lang.String)
	 */
	@Override
	public void saveArticle(String title, Author author, String description) {
		Optional<Author> fetchedAuthor = authorRepository.findByAuthorNameIgnoreCase(author.getAuthorName());
		if (fetchedAuthor.isPresent()) {
			author = fetchedAuthor.get();
		} else {
			authorRepository.save(metaDataUtil.setMetaData(author));
		}
		articleRepository.save(metaDataUtil.setMetaData(new Article(author, title, description)));
	}

}
