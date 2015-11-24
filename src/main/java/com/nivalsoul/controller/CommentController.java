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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nivalsoul.model.Comment;
import com.nivalsoul.model.ResultPage;
import com.nivalsoul.service.CommentManager;
import com.nivalsoul.utils.JsonUtils;

@Controller
public class CommentController {
	private static final Logger log = Logger.getLogger(CommentController.class);
	
	@Autowired
	private CommentManager commentManager;
	
	@RequestMapping(value="/comment", method = RequestMethod.GET)  
    @ResponseBody
    public ResultPage<Comment> getComments(
    		@RequestParam(value = "articleId", defaultValue="0") Long articleId, 
    		@RequestParam(value = "page", defaultValue="0") Integer page, 
    		@RequestParam(value = "size", defaultValue="10") Integer size){
		log.info("get comments by articleId:"+articleId+" and page:"+page+" and size:"+size);
        return commentManager.getComments(articleId, 
        		new PageRequest(page, size, Direction.DESC, "id"));  
    }  
	
	@RequestMapping(value="/comment", method = RequestMethod.POST)  
	@ResponseBody
    public String saveComment(Comment comment, 
    		HttpServletRequest request){
		log.info("save comment:"+JsonUtils.beanToJson(comment));
		HttpSession session = request.getSession();
		String useraccount = (String) session .getAttribute("useraccount");
		String username = (String) session .getAttribute("username");
		if(useraccount!=null && !useraccount.equals("")){
			comment.setAuthor(useraccount);
			comment.setAuthor_name(username);
		}else{
			comment.setAuthor("visitor");
			comment.setAuthor_name("游客");
		}
		comment.setPub_time(new Timestamp(new Date().getTime()));
		comment.setPraise_num(0);
        commentManager.saveComment(comment);
        return "ok";
    }  
	
	@RequestMapping(value="/comment/{id}", method = RequestMethod.DELETE)  
	@ResponseBody
    public String getCommentById(@PathVariable("id") Long id){  
        log.info("delete comment by commentId:"+id);  
        commentManager.deleteComment(id);
        return "ok";
    }  
}
