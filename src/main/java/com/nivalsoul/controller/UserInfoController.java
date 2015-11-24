package com.nivalsoul.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.nivalsoul.model.Userinfo;
import com.nivalsoul.service.UserInfoManager;
import com.nivalsoul.utils.MD5;

@Controller  
public class UserInfoController {
	private static final Logger log = Logger.getLogger(UserInfoController.class);
	
	@Autowired
	private UserInfoManager userInfoManager;
	
	/**
	 * 访问用户页面
	 * @param model
	 * @param useraccount
	 * @return
	 */
	@RequestMapping(value="/user/{useraccount}", method = RequestMethod.GET)  
    public String getUserInfo(Model model,
    		@PathVariable("useraccount") String useraccount){
		log.info("get userinfo by useraccount:"+useraccount);
		Userinfo userinfo = userInfoManager.findByUseraccount(useraccount);
		if (userinfo == null) {
			userinfo = new Userinfo();
		}
		model.addAttribute("userinfo", userinfo);
        return "userinfo";
    }  
	
	/**
	 * 注册
	 * @param userinfo
	 * @return
	 */
	@RequestMapping(value="/user", method = RequestMethod.POST)  
	@ResponseBody
    public String saveUserInfo(Userinfo userinfo,
    		HttpServletRequest request,HttpServletResponse response) {   
		log.info("save Userinfo:"+JSON.toJSONString(userinfo));
		if(userInfoManager.findByUseraccount(userinfo.getUseraccount())!=null)
			return "用户名已经存在！";
		userinfo.setPassword(MD5.getHashString(userinfo.getPassword()));
		Timestamp timestamp = new Timestamp(new Date().getTime());
		userinfo.setRegtime(timestamp);
		userinfo.setLastlogintime(timestamp);
		userinfo.setPhoto("images/defaultphoto64.png");
        userInfoManager.saveUserInfo(userinfo);
        //设置session和cookie
      	setSessionCookie(request, response, userinfo);
        return "ok";
    }  
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/user/{id}", method = RequestMethod.DELETE)  
	@ResponseBody
    public String deleteUser(@PathVariable("id") Integer id){  
        log.info("delete User by id:"+id);  
        userInfoManager.deleteUserInfo(id);
        return "ok";
    }  
	
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @param useraccount
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/user/login", method = RequestMethod.POST)  
	@ResponseBody
    public Map<String, Object> login(
    		HttpServletRequest request,HttpServletResponse response,
    		@RequestParam(value = "useraccount") String useraccount, 
			@RequestParam(value = "password") String password){   
		log.info("user: "+useraccount+" login...");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("info", "登录成功");
		Userinfo userinfo = userInfoManager.findByUseraccount(useraccount);
		if(userinfo ==null){
			result.put("code", 1);
			result.put("info", "用户名不存在");
			return result;
		}
		if(!userinfo.getPassword().equals(MD5.getHashString(password))){
			result.put("code", 2);
			result.put("info", "密码错误");
			return result;
		}
		//设置session和cookie
		setSessionCookie(request, response, userinfo);
	    //更新登录信息
		userinfo.setLastlogintime(new Timestamp(new Date().getTime()));
		userInfoManager.saveUserInfo(userinfo);
		
        return result;
    }
	
	/**
	 * 注销
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/user/logout", method = RequestMethod.POST)  
	@ResponseBody
    public String logout(HttpServletRequest request){   
		//设置session
	    HttpSession session = request.getSession();
		log.info("user: "+session.getAttribute("useraccount")+" logout...");
		session.removeAttribute("useraccount");
		session.removeAttribute("username");
	    
        return "ok";
    }

	private void setSessionCookie(HttpServletRequest request,
			HttpServletResponse response, Userinfo userinfo) {
		//设置session
		HttpSession session = request.getSession();
		session.setAttribute("useraccount", userinfo.getUseraccount());
		session.setAttribute("username", userinfo.getUsername());
		session.setMaxInactiveInterval(3*3600);
		//设置cookie
		Cookie cookie = new Cookie("useraccount",userinfo.getUseraccount());
		// 不设置的话，则cookies不写入硬盘,而是写在内存,只在当前页面有用,以秒为单位
		cookie.setMaxAge(3*3600);
		//设置路径，这个路径即该工程下都可以访问该cookie 如果不设置路径，那么只有设置该cookie路径及其子路径可以访问
		cookie.setPath("/");
		response.addCookie(cookie);
		try {
			cookie = new Cookie("username", URLEncoder.encode(userinfo.getUsername(), "UTF-8"));
			cookie.setMaxAge(3*3600);
			cookie.setPath("/");
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}  
	
	
}
