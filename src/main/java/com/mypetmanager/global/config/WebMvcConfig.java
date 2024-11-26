package com.mypetmanager.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	/**
	 *	web mvc interceptor setting
	 * @param registry
	 * @author kubel
	 * 2024. 10. 3.
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//		registry.addInterceptor(new SessionCheckInterceptor())
		//			.addPathPatterns("/**") // 모든 경로 포함
		//.excludePathPatterns("/employee/search") // 제외 경로
		;
	}

}
