package com.nivalsoul.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.nivalsoul.model.Userinfo;

@Transactional
public interface UserInfoDao extends PagingAndSortingRepository<Userinfo, Integer> {

	public Userinfo findByUseraccount(String userAccount);
	
}
