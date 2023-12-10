package com.mypetmanager.global.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.mypetmanager.integration.repository", // 패키지 경로를 적절히 변경
        includeFilters = @ComponentScan.Filter(
                type = FilterType.REGEX,
                pattern = ".*Repository$" // "Repository"로 끝나는 클래스를 스캔
        )
)
//@EnableAspectJAutoProxy
public class AppConfig {
	
	  
}
