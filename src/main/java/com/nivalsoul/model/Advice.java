package com.nivalsoul.model;

import java.util.Date;
import java.sql.*; 
import java.io.*; 

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "advice")
public class Advice implements Serializable { 

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column
	private String title;
	@Column
	private String description;
	@Column
	private String contact;
	@Column
	private Timestamp subtime;
	@Column
	private String answer;
	@Column
	private String category;
	@Column
	private Timestamp dealtime;
	@Column
	private Integer hits;

	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return this.id;
	}

	public void setTitle(String title){
		this.title = title;
	}
	public String getTitle(){
		return this.title;
	}

	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return this.description;
	}

	public void setContact(String contact){
		this.contact = contact;
	}
	public String getContact(){
		return this.contact;
	}

	public void setSubtime(Timestamp subtime){
		this.subtime = subtime;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
	public Timestamp getSubtime(){
		return this.subtime;
	}

	public void setAnswer(String answer){
		this.answer = answer;
	}
	public String getAnswer(){
		return this.answer;
	}

	public void setCategory(String category){
		this.category = category;
	}
	public String getCategory(){
		return this.category;
	}

	public void setDealtime(Timestamp dealtime){
		this.dealtime = dealtime;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
	public Timestamp getDealtime(){
		return this.dealtime;
	}

	public void setHits(Integer hits){
		this.hits = hits;
	}
	public Integer getHits(){
		return this.hits;
	}
}
