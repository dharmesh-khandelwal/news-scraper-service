/**
 * 
 */
package com.handzap.newsscraper.service;

import com.handzap.newsscraper.dto.AuthorDTO;
import com.handzap.newsscraper.dto.ResponseDTO;
import com.handzap.newsscraper.exception.DtoEntityMappingException;

/**
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
public interface NewsAuthorService {

	/**
	 * @return
	 * @throws DtoEntityMappingException 
	 */
	ResponseDTO<AuthorDTO> getAllAuthors() throws DtoEntityMappingException;

}
