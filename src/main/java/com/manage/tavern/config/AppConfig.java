/**
 * 
 */
package com.manage.tavern.config;

import com.manage.tavern.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author t-xiabin
 *
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {

	@Bean
	public TokenInterceptor tokenInterceptor() {
		return new TokenInterceptor();
	}


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(tokenInterceptor()).addPathPatterns("/**")
			.excludePathPatterns(
					"/system/doLogin/**",
					"/system/**",
					"/suite/receive/**",
					"/test/**",
					"/html/**",
					"/common/**",
					"/static/**",
					"/own/**",
					"/*");
	}
	
}
