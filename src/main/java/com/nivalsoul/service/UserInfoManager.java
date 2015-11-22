package com.nivalsoul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nivalsoul.dao.UserInfoDao;
import com.nivalsoul.model.Userinfo;

@Service
public class UserInfoManager {

    @Autowired
    private UserInfoDao userInfoDao;

    public void saveUserInfo(Userinfo userInfo) {
        userInfoDao.save(userInfo);
    }

    public void deleteUserInfo(Integer id) {
    	Userinfo userInfo = userInfoDao.findOne(id);
        if (userInfo != null) {
            userInfoDao.delete(id);
        } else {
            throw new IllegalStateException("no such userInfoId: " + id);
        }
    }

    public Iterable<Userinfo> getAllUserInfos() {
        Iterable<Userinfo> alluserInfos = userInfoDao.findAll();
        return alluserInfos;
    }
    
    public Userinfo getUserInfoById(Integer id) {
    	Userinfo userInfo = userInfoDao.findOne(id);
    	return userInfo;
	}

    public void updateUserInfo(Userinfo userInfo) {
        userInfoDao.save(userInfo);
    }
    
    public Userinfo findByUseraccount(String userAccount){
    	Userinfo userInfo = userInfoDao.findByUseraccount(userAccount);
        return userInfo;
    }
    
}
