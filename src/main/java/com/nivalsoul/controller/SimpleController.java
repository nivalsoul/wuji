package com.nivalsoul.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nivalsoul.ApplicationConfig;
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
			Gongzhonghao.startTimer(config, 6, 1, 0);
			started = true;
		}
		return "文章抓取任务已启动...";
	}

}
