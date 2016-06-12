package com.nivalsoul;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;


public class DoInit implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//防止重复执行。
        if(event.getApplicationContext().getParent() == null){
        	try {
        		// 取到上下文中配置文件中的信息
        		System.out.println("hahahahhahah");
        		ApplicationConfig ac = event.getApplicationContext().getBean(ApplicationConfig.class);
        		if(ac.isStartTimer()){
        			Map<String, String> ds = ac.getDatasource();
        			Map<String, String> config = new HashMap<String, String>();
        			config.put("driver", ds.get("driverClassName"));
        			config.put("url", ds.get("url"));
        			config.put("userName", ds.get("username"));
        			config.put("password", ds.get("password"));
        			//启动文章抓取任务定时器
        			//Gongzhonghao.startTimer(config, 6, 1, 0, 24*60*60*1000);
        			System.out.println(ds);
        		}
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
        }
	}
}
