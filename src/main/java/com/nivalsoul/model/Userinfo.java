package com.nivalsoul.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "userinfo")
public class Userinfo implements Serializable { 
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column
	private String useraccount;
	@Column
	private String username;
	@Column
	private String thirdaccount;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	private String photo;
	@Column
	private String gender;
	@Column
	private Date birthday;
	@Column
	private String address;
	@Column
	private Timestamp regtime;
	@Column
	private String signature;
	@Column
	private Timestamp lastlogintime;
	@Column
	private Integer treasure;
	@Column
	private Integer level;
	@Column
	private String honor;
	@Column
	private Integer exp;
	@Column
	private Byte ifpush;
	@Column
	private Integer contribution_num;
	@Column
	private Integer visit_num;
	@Column
	private Integer follow_num;

	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return this.id;
	}

	public void setUseraccount(String useraccount){
		this.useraccount = useraccount;
	}
	public String getUseraccount(){
		return this.useraccount;
	}

	public void setUsername(String username){
		this.username = username;
	}
	public String getUsername(){
		return this.username;
	}

	public void setThirdaccount(String thirdaccount){
		this.thirdaccount = thirdaccount;
	}
	public String getThirdaccount(){
		return this.thirdaccount;
	}

	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return this.password;
	}

	public void setEmail(String email){
		this.email = email;
	}
	public String getEmail(){
		return this.email;
	}

	public void setPhoto(String photo){
		this.photo = photo;
	}
	public String getPhoto(){
		return this.photo;
	}

	public void setGender(String gender){
		this.gender = gender;
	}
	public String getGender(){
		return this.gender;
	}

	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}
	public Date getBirthday(){
		return this.birthday;
	}

	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress(){
		return this.address;
	}

	public void setRegtime(Timestamp regtime){
		this.regtime = regtime;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
	public Timestamp getRegtime(){
		return this.regtime;
	}

	public void setSignature(String signature){
		this.signature = signature;
	}
	public String getSignature(){
		return this.signature;
	}

	public void setLastlogintime(Timestamp lastlogintime){
		this.lastlogintime = lastlogintime;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
	public Timestamp getLastlogintime(){
		return this.lastlogintime;
	}

	public void setTreasure(Integer treasure){
		this.treasure = treasure;
	}
	public Integer getTreasure(){
		return this.treasure;
	}

	public void setLevel(Integer level){
		this.level = level;
	}
	public Integer getLevel(){
		return this.level;
	}

	public void setHonor(String honor){
		this.honor = honor;
	}
	public String getHonor(){
		return this.honor;
	}

	public void setExp(Integer exp){
		this.exp = exp;
	}
	public Integer getExp(){
		return this.exp;
	}

	public void setIfpush(Byte ifpush){
		this.ifpush = ifpush;
	}
	public Byte getIfpush(){
		return this.ifpush;
	}

	public void setContribution_num(Integer contribution_num){
		this.contribution_num = contribution_num;
	}
	public Integer getContribution_num(){
		return this.contribution_num;
	}

	public void setVisit_num(Integer visit_num){
		this.visit_num = visit_num;
	}
	public Integer getVisit_num(){
		return this.visit_num;
	}

	public void setFollow_num(Integer follow_num){
		this.follow_num = follow_num;
	}
	public Integer getFollow_num(){
		return this.follow_num;
	}
}
