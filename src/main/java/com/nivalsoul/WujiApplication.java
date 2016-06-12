package com.nivalsoul;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.ContextLoaderListener;

@SpringBootApplication 
@EnableConfigurationProperties({ApplicationConfig.class})
public class WujiApplication extends SpringBootServletInitializer {
	
	@Override
	public SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WujiApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WujiApplication.class);
	    app.addListeners(new DoInit());
	    app.run(args);
	}
	
	@Bean
	protected MyListener listener() {
		return new MyListener();
	}

}
