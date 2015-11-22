package com.nivalsoul;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="spring")  
public class ApplicationConfig {
	private Map<String, String> datasource;
	private boolean startTimer;

	public Map<String, String> getDatasource() {
		return datasource;
	}

	public void setDatasource(Map<String, String> datasource) {
		this.datasource = datasource;
	}

	public boolean isStartTimer() {
		return startTimer;
	}

	public void setStartTimer(boolean startTimer) {
		this.startTimer = startTimer;
	}
    
}
