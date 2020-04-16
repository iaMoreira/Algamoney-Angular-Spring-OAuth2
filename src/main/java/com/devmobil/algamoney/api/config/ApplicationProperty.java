package com.devmobil.algamoney.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("api")
public class ApplicationProperty {
	
	private final Security security = new Security();
	private final Cors cors = new Cors();
	
	public Security getSecurity () {
		return this.security;
	}
	
	public Cors getCors () {
		return this.cors;
	}
	
	
	public static class Security {
		private Boolean enableHttps;

		public Boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(Boolean enableHttps) {
			this.enableHttps = enableHttps;
		}	
	}
	
	
	public static class Cors {
		private String allowUrl;

		public String getAllowUrl() {
			return allowUrl;
		}

		public void setAllowUrl(String allowUrl) {
			this.allowUrl = allowUrl;
		}
	}
}
