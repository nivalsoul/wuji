package com.nivalsoul.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nivalsoul.dao.CommentDao;
import com.nivalsoul.model.Comment;
import com.nivalsoul.model.CommentBean;
import com.nivalsoul.model.ResultPage;

@Service
public class CommentManager {

    @Autowired
    private CommentDao commentDao;

    public void saveComment(Comment comment) {
        commentDao.save(comment);
    }

    public void deleteComment(Long id) {
        Comment comment = commentDao.findOne(id);
        if (comment != null) {
            commentDao.delete(id);
        } else {
            throw new IllegalStateException("no such commentId: " + id);
        }
    }

    public Iterable<Comment> getAllComments(Long articleId) {
        Iterable<Comment> allcomments = commentDao.findByArticleId(articleId);
        return allcomments;
    }
    
    public ResultPage<Comment> getComments(Long articleId, Pageable pageable){
    	Page<Comment> comments = commentDao.findByArticleId(articleId, pageable);
    	ResultPage<Comment> resultPage = new ResultPage<Comment>(comments);
    	List<Comment> list = resultPage.getContent();
    	for (int i = 0; i < list.size(); i++) {
			Comment comment = list.get(i);
			Long sourceId = comment.getSource_id();
			CommentBean commentBean = new CommentBean(comment);
			commentBean.setPhotoUrl(null);
			list.set(i, commentBean);
			while(sourceId != null && !sourceId.toString().equals("")){
				Comment parentComment = commentDao.findOne(sourceId);
				CommentBean commentBean2 = new CommentBean(parentComment);
				commentBean2.setPhotoUrl(null);
				commentBean.setParentComment(commentBean2);
				sourceId = parentComment.getSource_id();
				commentBean = commentBean2;
			}
		}
        return resultPage;
    }
    
    public Comment getCommentById(Long id) {
    	Comment comment = commentDao.findOne(id);
    	return comment;
	}

    public void updateComment(Comment comment) {
        commentDao.save(comment);
    }
}
