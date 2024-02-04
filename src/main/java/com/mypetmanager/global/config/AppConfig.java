package com.mypetmanager.global.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "com.mypetmanager.integration.repository", // 패키지 경로를 적절히 변경
	includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*Repository$" // "Repository"로 끝나는 클래스를 스캔
	))
public class AppConfig {

	//	@Bean
	//	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	//		return builder.build();
	//	}

	//	@Bean
	//	public ObservationHandler<Observation.Context> observationTextPublisher() {
	//		return new ObservationTextPublisher(log::info);
	//	}

	//	@Bean
	//	public OtlpGrpcSpanExporter otlpHttpSpanExporter(@Value("${otlp.tracing.endpoint}")
	//	String url) throws Exception {
	//		if (url == null) {
	//			throw new Exception("dd");
	//		}
	//
	//		return OtlpGrpcSpanExporter.builder().setEndpoint(url).build();
	//	}
}
