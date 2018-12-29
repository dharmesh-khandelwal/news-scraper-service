/**
 * 
 */
package com.handzap.newsscraper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.handzap.newsscraper.dto.ArticleDTO;
import com.handzap.newsscraper.dto.AuthorDTO;
import com.handzap.newsscraper.dto.ResponseDTO;
import com.handzap.newsscraper.exception.DtoEntityMappingException;
import com.handzap.newsscraper.service.NewsArticleService;
import com.handzap.newsscraper.service.NewsAuthorService;

import io.swagger.annotations.Api;

/**
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
@RestController
@Api(tags = { "News Scrapper" })
@RequestMapping("/v1.0")
public class NewsScraperController {

	@Autowired
	NewsAuthorService newsAuthorService;

	@Autowired
	NewsArticleService newsArticleService;

	@GetMapping(value = "/authors")
	public ResponseEntity<ResponseDTO<AuthorDTO>> getAllAuthors() throws DtoEntityMappingException {
		return new ResponseEntity<>(newsAuthorService.getAllAuthors(), HttpStatus.OK);
	}

	@GetMapping(value = "/articles", params = "authorName")
	public ResponseEntity<ResponseDTO<ArticleDTO>> getArticlesByAuthor(@RequestParam String authorName) throws DtoEntityMappingException {
		return new ResponseEntity<>(newsArticleService.getArticlesByAuthor(authorName), HttpStatus.OK);
	}

	@GetMapping(value = "/articles", params = { "title", "description" })
	public ResponseEntity<ResponseDTO<ArticleDTO>> getArticlesByTitleAndDescription(@RequestParam String title,
			@RequestParam String description) throws DtoEntityMappingException {
		return new ResponseEntity<>(newsArticleService.getArticlesByTitleAndDescription(title, description),
				HttpStatus.OK);
	}

}
