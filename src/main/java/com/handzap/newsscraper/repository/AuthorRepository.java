package com.handzap.newsscraper.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.handzap.newsscraper.entity.Author;

/**
 * Repository for Author to perform CURD operation
 * 
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

	Optional<Author> findByAuthorNameIgnoreCase(String name);

	List<Author> findByAuthorNameIgnoreCaseContaining(String name);

}
