package com.nivalsoul.model;

import java.lang.reflect.Method;

public class CommentBean extends Comment {
	private static final long serialVersionUID = 1L;
	
	private Comment parentComment;
	private String photoUrl;

	public Comment getParentComment() {
		return parentComment;
	}

	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	public CommentBean(Comment comment){
		Method[] methods = Comment.class.getDeclaredMethods();
		for (Method method : methods) {
			String name = method.getName();
			if(name.startsWith("set"))
				continue;
			try {
				Object value = method.invoke(comment);
				if(value!=null){
					Method setMethod = this.getClass().getMethod("set"+name.substring(3), value.getClass());
					setMethod.invoke(this, value);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
