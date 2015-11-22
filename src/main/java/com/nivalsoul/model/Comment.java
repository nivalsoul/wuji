package com.nivalsoul.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "comment")
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Long article_id;
	@Column
	private String author;
	@Column
	private String author_name;
	@Column
	private String content;
	@Column
	private Timestamp pub_time;
	@Column
	private Integer praise_num;
	@Column
	private Long source_id;
	@Column
	private Long ref_comment_id;
	@Column
	private String quotation;

	public void setId(Long id){
		this.id = id;
	}
	public Long getId(){
		return this.id;
	}
	public Long getArticle_id() {
		return article_id;
	}
	public void setArticle_id(Long articleId) {
		this.article_id = articleId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	public Timestamp getPub_time() {
		return pub_time;
	}
	public void setPub_time(Timestamp pub_time) {
		this.pub_time = pub_time;
	}
	public Integer getPraise_num() {
		return praise_num;
	}
	public void setPraise_num(Integer praise_num) {
		this.praise_num = praise_num;
	}
	public Long getSource_id() {
		return source_id;
	}
	public void setSource_id(Long source_id) {
		this.source_id = source_id;
	}
	public Long getRef_comment_id() {
		return ref_comment_id;
	}
	public void setRef_comment_id(Long ref_comment_id) {
		this.ref_comment_id = ref_comment_id;
	}
	public String getQuotation() {
		return quotation;
	}
	public void setQuotation(String quotation) {
		this.quotation = quotation;
	}	

}
