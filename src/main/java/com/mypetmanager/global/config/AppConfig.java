package com.mypetmanager.global.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import io.micrometer.observation.ObservationTextPublisher;
import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;

@Configuration
@ComponentScan(basePackages = "com.mypetmanager.integration.repository", // 패키지 경로를 적절히 변경
	includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*Repository$" // "Repository"로 끝나는 클래스를 스캔
	))
public class AppConfig {

	private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}


	@Bean
	OtlpHttpSpanExporter otlpHttpSpanExporter(@Value("${otlp.tracing.endpoint}")
	String url) {
		if (url != null) {
			return OtlpHttpSpanExporter.builder()
				.setEndpoint(url)
				.build();
		} else {
			return null;
		}
	}

	@Bean
	public ObservationHandler<Observation.Context> observationTextPublisher() {
		return new ObservationTextPublisher(log::info);
	}

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
