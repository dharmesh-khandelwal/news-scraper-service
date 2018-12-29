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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller for news scraping service. Contains api for getting all authors,
 * searching by author name and searching by title and description
 * 
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
	@ApiOperation(value = "Retrieve all authors", notes = "Retrieve all authors")
	public ResponseEntity<ResponseDTO<AuthorDTO>> getAllAuthors() throws DtoEntityMappingException {
		return new ResponseEntity<>(newsAuthorService.getAllAuthors(), HttpStatus.OK);
	}

	@GetMapping(value = "/articles", params = "authorName")
	@ApiOperation(value = "Search articles based on author", notes = "Search articles whose author-name contains given input")
	@ApiResponses({
			@ApiResponse(code = 200, message = "When articles are successfully fetched"),
			@ApiResponse(code = 500, message = "While error occur while fetching article") })
	public ResponseEntity<ResponseDTO<ArticleDTO>> getArticlesByAuthor(@RequestParam String authorName)
			throws DtoEntityMappingException {
		return new ResponseEntity<>(newsArticleService.getArticlesByAuthor(authorName), HttpStatus.OK);
	}

	@GetMapping(value = "/articles", params = { "title", "description" })
	@ApiOperation(value = "Search articles based on title and description", notes = "Search articles whose title and description contains given input")
	@ApiResponses({
			@ApiResponse(code = 200, message = "When articles are successfully fetched"),
			@ApiResponse(code = 500, message = "While error occur while fetching article") })
	public ResponseEntity<ResponseDTO<ArticleDTO>> getArticlesByTitleAndDescription(@RequestParam String title,
			@RequestParam String description) throws DtoEntityMappingException {
		return new ResponseEntity<>(newsArticleService.getArticlesByTitleAndDescription(title, description),
				HttpStatus.OK);
	}

}
