package com.nivalsoul.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.nivalsoul.model.Comment;

@Transactional
public interface CommentDao extends PagingAndSortingRepository<Comment, Long> {

	@Query("select c from Comment c where c.article_id=?1")
	public Iterable<Comment> findByArticleId(Long articleId);

	@Query("select c from Comment c where c.article_id=?1")
	public Page<Comment> findByArticleId(Long articleId, Pageable pageable);
}
