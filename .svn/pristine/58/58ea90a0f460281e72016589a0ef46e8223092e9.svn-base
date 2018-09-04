package com.novel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * 解决跨域问题
 * @author Administrator
 *
 */
@Configuration
public class CrossDomainConfig extends WebMvcConfigurerAdapter {
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins(new String[] { "*" })
				.allowedMethods(new String[] { "GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS" })
				.allowCredentials(false).maxAge(3600L);
	}
}
