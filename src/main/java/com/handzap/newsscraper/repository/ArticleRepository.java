/**
 * 
 */
package com.handzap.newsscraper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.handzap.newsscraper.entity.Article;

/**
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

	List<Article> findArticlesByTitleIgnoreCaseContainingAndDescriptionIgnoreCaseContaining(String title,String description);

}
