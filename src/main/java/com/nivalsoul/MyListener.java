package com.nivalsoul;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class MyListener implements ApplicationListener<ContextRefreshedEvent> {
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//防止重复执行。
        if(event.getApplicationContext().getParent() == null){
			// 取到上下文中配置文件中的信息
			System.out.println("===hahahahhahah");
			ApplicationConfig ac = event.getApplicationContext().getBean(ApplicationConfig.class);
			System.out.println(ac.getDatasource());
        }
	}


}
