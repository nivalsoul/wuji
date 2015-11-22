package com.nivalsoul.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.nivalsoul.model.Article;

@Transactional
public interface ArticleDao extends PagingAndSortingRepository<Article, Long> {

	Page<Article> findBySource(String source, Pageable pageable);
	
	@Query("select a from Article a where a.source = ?1 and a.article_type = ?2")
	Page<Article> findBySourceArticleType(String source, String article_type, Pageable pageable);
}
