package com.nivalsoul;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
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
	    ConfigurableApplicationContext context = app.run(args);
	    ConfigurableEnvironment env = context.getEnvironment();
	    System.out.println("***程序已经启动，请访问：http://localhost:"
	    		+env.getProperty("server.port")+env.getProperty("server.context-path")+"***");
	}
	
	@Bean
	protected MyListener listener() {
		return new MyListener();
	}
	
	/**
	 * H2数据库控制台路径，启动后访问http://localhost:8888/console即可管理数据库
	 * @return
	 */
	@Bean
    protected ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }

}
