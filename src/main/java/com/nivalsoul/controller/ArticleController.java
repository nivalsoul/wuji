package com.nivalsoul.controller;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
			@RequestParam(value = "source", defaultValue="原创") String source, 
			@RequestParam(value = "article_type", required = false) String article_type, 
			@RequestParam(value = "page", defaultValue="0") Integer page, 
			@RequestParam(value = "size", defaultValue="10") Integer size){
		log.info("get articles by source:"+source+" and article_type:"+article_type);
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
    public String saveArticle(Article article, HttpServletRequest request){   
		HttpSession session = request.getSession();
		String useraccount = (String) session .getAttribute("useraccount");
		String username = (String) session .getAttribute("username");
		if(useraccount==null || useraccount.equals("")){
			return "对不起，你未登录或者超过了有效期，请登录后重试！";
		}
		if(article.getId() == null){
			log.info("add article:"+JSON.toJSONString(article));
			article.setArticle_content(article.getArticle_content().replaceAll("\r\n", "<br/>"));
			article.setQuotation(article.getQuotation().replaceAll("\r\n", "<br/>"));
			article.setAuthor(useraccount);
			article.setAuthor_name(username);
			article.setPub_time(new Timestamp(new Date().getTime()));
			article.setComment_num(0);
			article.setRead_num(0);
			article.setOriginal((byte) 1);
			article.setArticle_link("");
			articleManager.saveArticle(article);
		}else{
			log.info("update article:"+JSON.toJSONString(article));
			Article oldData = articleManager.getArticleById(article.getId());
			if (oldData.getAuthor().equals(useraccount) || useraccount.equals("admin")) {
				oldData.setArticle_title(article.getArticle_title());
				oldData.setArticle_content(article.getArticle_content());
				oldData.setArticle_title(article.getArticle_type());
				oldData.setQuotation(article.getQuotation());
				articleManager.saveArticle(oldData);
			}else{
				return "对不起，你没有修改权限";
			}
		}
        
        return "保存成功！";
    }  
	
	@RequestMapping(value="/article/{id}.html", method = RequestMethod.GET)  
    public String getArticlePageById(@PathVariable("id") Long id, Model model){  
        log.info("get article page by articleId:"+id);  
        Article article = articleManager.getArticleById(id);
        model.addAttribute("article", article);
        article.setRead_num(article.getRead_num()+1);
        articleManager.saveArticle(article);
        return "view";
    }  
	
	@RequestMapping(value="/article/{id}", method = RequestMethod.GET)  
	@ResponseBody
	public Article getArticleById(@PathVariable("id") Long id){  
		log.info("get article info by articleId:"+id);  
		Article article = articleManager.getArticleById(id);
		return article;
	}  
	
}
