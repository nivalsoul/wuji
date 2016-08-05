package com.nivalsoul.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nivalsoul.dao.ArticleDao;
import com.nivalsoul.dao.TechInfoDao;
import com.nivalsoul.model.Article;
import com.nivalsoul.model.TechInfo;

@Service
public class ArticleManager {

    @Autowired
    private ArticleDao articleDao;
    
    @Autowired
    private TechInfoDao techInfoDao;

    public void saveArticle(Article article) {
        articleDao.save(article);
    }

    public void deleteArticle(Long id) {
        Article article = articleDao.findOne(id);
        if (article != null) {
            articleDao.delete(id);
        } else {
            throw new IllegalStateException("no such articleId: " + id);
        }
    }

    public Iterable<Article> getAllArticles() {
        Iterable<Article> allarticles = articleDao.findAll();
        return allarticles;
    }
    
    public Page<Article> getArticles(Pageable pageable){
    	Page<Article> articles = articleDao.findAll(pageable);
        return articles;
    }
    
    public Article getArticleById(Long id) {
    	Article article = articleDao.findOne(id);
    	return article;
	}

    public void updateArticle(Article article) {
        articleDao.save(article);
    }
    
    public Page<Article> findBySource(String source, Pageable pageable){
    	Page<Article> articles = articleDao.findBySource(source, pageable);
        return articles;
    }
    
    public Page<Article> findBySourceArticleType(
    		String source, String article_type, Pageable pageable){
    	Page<Article> articles = articleDao
    			.findBySourceArticleType(source, article_type, pageable);
        return articles;
    }

	public Page<TechInfo> findITArticles(String source, String article_type, Pageable pageable) {
		if (article_type==null || "".equals(article_type)) {
			return techInfoDao.findAll(pageable);
		}else {
			return techInfoDao.findBySource(source, pageable);  
		}
	}
}
