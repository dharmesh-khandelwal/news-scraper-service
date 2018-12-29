/**
 * 
 */
package com.handzap.newsscraper.service;

import com.handzap.newsscraper.dto.AuthorDTO;
import com.handzap.newsscraper.dto.ResponseDTO;

/**
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
public interface NewsAuthorService {

	/**
	 * @return
	 */
	ResponseDTO<AuthorDTO> getAllAuthors();

}
