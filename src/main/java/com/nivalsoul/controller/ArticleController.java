package com.nivalsoul.controller;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.nivalsoul.model.Article;
import com.nivalsoul.service.ArticleManager;

@Controller
public class ArticleController {
	private static final Logger log = Logger.getLogger(ArticleController.class);
	
	@Autowired
	private ArticleManager articleManager;
	
	@RequestMapping(value="/article", method = RequestMethod.GET)  
    @ResponseBody
    public Iterable<Article> getArticles(
    		@RequestParam(value = "page", defaultValue="0") Integer page, 
    		@RequestParam(value = "size", defaultValue="10") Integer size){
		log.info("get articles by page:"+page+" and size:"+size);
        return articleManager.getArticles(new PageRequest(page, size, Direction.DESC, "id"));  
    }  
	
	@RequestMapping(value="/article2", method = RequestMethod.GET)  
	@ResponseBody
	public Iterable<Article> getArticles2(
			@RequestParam(value = "source", defaultValue="原创") String source, 
			@RequestParam(value = "article_type", required = false) String article_type, 
			@RequestParam(value = "page", defaultValue="0") Integer page, 
			@RequestParam(value = "size", defaultValue="10") Integer size){
		log.info("get articles2 by source:"+source+" and article_type:"+article_type);
		if (article_type==null || "".equals(article_type)) {
			return articleManager.findBySource(source, 
					new PageRequest(page, size, Direction.DESC, "id"));
		}else {
			return articleManager.findBySourceArticleType(
					source,article_type, new PageRequest(page, size, Direction.DESC, "id"));  
		}
	}  
	
	@RequestMapping(value="/article", method = RequestMethod.POST)  
	@ResponseBody
    public String saveArticle(Article article){   
		log.info("save article:"+JSON.toJSONString(article));
		article.setArticle_content(article.getArticle_content().replaceAll("\r\n", "<br/>"));
		article.setQuotation(article.getQuotation().replaceAll("\r\n", "<br/>"));
		article.setAuthor("admin");
		article.setAuthor_name("管理员");
		article.setPub_time(new Timestamp(new Date().getTime()));
		article.setComment_num(0);
		article.setRead_num(0);
		article.setOriginal((byte) 1);
		article.setArticle_link("");
        articleManager.saveArticle(article);
        return "保存成功！";
    }  
	
	@RequestMapping(value="/article/{id}.html", method = RequestMethod.GET)  
    public String getArticleById(@PathVariable("id") Long id, Model model){  
        log.info("get article by articleId:"+id);  
        Article article = articleManager.getArticleById(id);
        model.addAttribute("article", article);
        article.setRead_num(article.getRead_num()+1);
        articleManager.saveArticle(article);
        return "view";
    }  
}
