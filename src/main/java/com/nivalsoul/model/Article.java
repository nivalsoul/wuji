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
@Table(name = "article")
public class Article implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String article_title;
	@Column
	private String article_content;
	@Column
	private String author;
	@Column
	private String author_name;
	@Column
	private Timestamp pub_time;
	@Column
	private Integer comment_num;
	@Column
	private String article_type;
	@Column
	private String quotation;
	@Column
	private Byte original;
	@Column
	private String article_link;
	@Column
	private String source;
	@Column
	private Long reference_id;
	@Column
	private Integer read_num;

	public Integer getRead_num() {
		return read_num;
	}
	public void setRead_num(Integer read_num) {
		this.read_num = read_num;
	}
	public Long getReference_id() {
		return reference_id;
	}
	public void setReference_id(Long reference_id) {
		this.reference_id = reference_id;
	}
	public void setId(Long id){
		this.id = id;
	}
	public Long getId(){
		return this.id;
	}

	public void setArticle_title(String article_title){
		this.article_title = article_title;
	}
	public String getArticle_title(){
		return this.article_title;
	}

	public void setArticle_content(String article_content){
		this.article_content = article_content;
	}
	public String getArticle_content(){
		return this.article_content;
	}

	public void setAuthor(String author){
		this.author = author;
	}
	public String getAuthor(){
		return this.author;
	}

	public void setAuthor_name(String author_name){
		this.author_name = author_name;
	}
	public String getAuthor_name(){
		return this.author_name;
	}

	public void setPub_time(Timestamp pub_time){
		this.pub_time = pub_time;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	public Timestamp getPub_time(){
		return this.pub_time;
	}

	public void setComment_num(Integer comment_num){
		this.comment_num = comment_num;
	}
	public Integer getComment_num(){
		return this.comment_num;
	}

	public void setArticle_type(String article_type){
		this.article_type = article_type;
	}
	public String getArticle_type(){
		return this.article_type;
	}

	public void setQuotation(String quotation){
		this.quotation = quotation;
	}
	public String getQuotation(){
		return this.quotation;
	}

	public void setOriginal(Byte original){
		this.original = original;
	}
	public Byte getOriginal(){
		return this.original;
	}

	public void setArticle_link(String article_link){
		this.article_link = article_link;
	}
	public String getArticle_link(){
		return this.article_link;
	}
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

}
