/**
 * 
 */
package com.handzap.newsscraper.service.impl;

import java.util.List;

import org.modelmapper.ConfigurationException;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.handzap.newsscraper.dto.AuthorDTO;
import com.handzap.newsscraper.dto.ResponseDTO;
import com.handzap.newsscraper.entity.Author;
import com.handzap.newsscraper.exception.DtoEntityMappingException;
import com.handzap.newsscraper.repository.AuthorRepository;
import com.handzap.newsscraper.service.NewsAuthorService;

/**
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
@Service
@Transactional
public class NewsAuthorServiceImpl implements NewsAuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseDTO<AuthorDTO> getAllAuthors() throws DtoEntityMappingException {
		ResponseDTO<AuthorDTO> authorResponseDTO = new ResponseDTO<>();
		List<Author> fetchedAuthors = authorRepository.findAll();
		List<AuthorDTO> authors = null;
		try {
			authors = modelMapper.map(fetchedAuthors, new TypeToken<List<AuthorDTO>>() {
			}.getType());
		} catch (IllegalArgumentException | ConfigurationException | MappingException exception) {
			throw new DtoEntityMappingException(exception);
		}
		authorResponseDTO.setResponse(authors);
		return authorResponseDTO;
	}

}
