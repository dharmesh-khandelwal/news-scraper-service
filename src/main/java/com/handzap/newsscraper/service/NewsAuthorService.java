package com.handzap.newsscraper.service;

import com.handzap.newsscraper.dto.AuthorDTO;
import com.handzap.newsscraper.dto.ResponseDTO;
import com.handzap.newsscraper.exception.DtoEntityMappingException;

/**
 * NewsAuthorService class with function to fetch authors
 * 
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
public interface NewsAuthorService {

	/**
	 * Function to get all authors
	 * 
	 * @return dto with list of authors
	 * @throws DtoEntityMappingException
	 *             if mapping fails
	 */
	ResponseDTO<AuthorDTO> getAllAuthors() throws DtoEntityMappingException;

}
