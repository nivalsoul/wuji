package com.nivalsoul.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nivalsoul.ApplicationConfig;
import com.nivalsoul.webspider.techinfo.GetFrom36kr;
import com.nivalsoul.webspider.techinfo.GetFromTmtpost;
import com.nivalsoul.webspider.techinfo.Tuicool;
import com.nivalsoul.webspider.weixin.Gongzhonghao;

//@EnableAutoConfiguration
@Controller  
@RequestMapping("/data") 
public class SimpleController {
	
	static boolean started =false;
	
	@Autowired
	ApplicationConfig ac;

	@RequestMapping(value ="", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> atricles(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "aaa");
		map.put("author", "abc");
		map.put("version", 1.0);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list.add(map);
		list.add(map);
		list.add(map);
        return list;
    }
	
	@RequestMapping(value ="/timer", method = RequestMethod.GET)
	@ResponseBody
	public String startTimer(){
		if(!started){
			Map<String, String> ds = ac.getDatasource();
			Map<String, String> config = new HashMap<String, String>();
			config.put("driver", ds.get("driverClassName"));
			config.put("url", ds.get("url"));
			config.put("userName", ds.get("username"));
			config.put("password", ds.get("password"));
			//启动文章抓取任务定时器
			Gongzhonghao.startTimer(config, 7, 5, 0, 2*60*60*1000);
			GetFrom36kr.startTimer(config, 22, 50, 0, 24*60*60*1000);
			GetFromTmtpost.startTimer(config, 23, 01, 0, 24*60*60*1000);
			Tuicool.startTimer(config, 6, 1, 0, 2*60*60*1000);
			started = true;
		}
		return "文章抓取任务已启动...";
	}
	
	@RequestMapping(value ="/file/{filename:.+}", method = RequestMethod.GET)
    public void getDocumentFile(HttpServletResponse response,
    		@PathVariable("filename") String filename){
		
		FileInputStream fis = null;
	    response.setContentType("image/png");
	    try {
	        ServletOutputStream out = response.getOutputStream();
	        File file = new File("G:/a.ico");
	        fis = new FileInputStream(file);
	        byte[] b = new byte[fis.available()];
	        fis.read(b);
	        out.write(b);
	        out.flush();
	    } catch (Exception e) {
	         e.printStackTrace();
	    } finally {
	        if (fis != null) {
	            try {
	               fis.close();
	            } catch (IOException e) {
	            e.printStackTrace();
	        }   
	          }
	    }
    }

}
