package com.mypetmanager.global.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.tracing.otlp.OtlpProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mypetmanager.global.handler.MyObservationHandler;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
@EnableConfigurationProperties(OtlpProperties.class)
public class OtelConfiguration {

	private final OtlpProperties otlpProperties;
	private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

	@Bean
	ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
		return new ObservedAspect(observationRegistry);
	}

	@Bean
	OtlpHttpSpanExporter otlpHttpSpanExporter(@Value("${otlp.tracing.endpoint}")
	String url) throws Exception {
		if (url == null) {
			throw new Exception("Url is null");
		}
		return OtlpHttpSpanExporter.builder()
			.setEndpoint(url)
			.build();
	}

	@Bean
	public MyObservationHandler customObservationHandler(Tracer tracer) {
		log.info("!!!!!!!!!!!!!! begin MyObservationHandler customObservationHandler");
		return new MyObservationHandler(tracer);
	}

	//	@Bean
	//    public OncePerRequestFilter traceContextFilter(Tracer tracer) {
	//        return new OncePerRequestFilter() {
	//
	//			@Override
	//			protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
	//				FilterChain filterChain) throws ServletException, IOException {
	//				// TODO Auto-generated method stub
	//					System.out.println("doFilterInternal =>> ");
	//					 Span currentSpan = tracer.spanBuilder("http-request").startSpan();
	//                try (var scope = currentSpan.makeCurrent()) {
	//                    // 여기에서 trace context를 사용하여 로깅 또는 다른 작업을 수행할 수 있습니다.
	//                    // trace context는 currentSpan으로부터 추출하여 사용 가능합니다.
	//
	//                    // 예: HTTP 요청 헤더에서 trace context 추출
	//                    Enumeration<String> headerNames = request.getHeaderNames();
	//                    while (headerNames.hasMoreElements()) {
	//                        String headerName = headerNames.nextElement();
	//                        String headerValue = request.getHeader(headerName);
	//                        // trace context 정보 활용
	//                    }
	//
	//                    // 요청 전처리 작업 수행
	//                    filterChain.doFilter(request, response);
	//
	//                    // 응답 후처리 작업 수행
	//                } finally {
	//                    currentSpan.end();
	//                }
	//			}
	//
	//			};
	//	 }




	// @Bean
	// public ObservationHandler<Observation.Context> observationTextPublisher() {
	// 	System.out.println("  ObservationHandler start === ");
	// 	System.out.println("");

	// 	return new ObservationTextPublisher(log::info);
	// }

	// @Bean
	// public TextMapPropagator jaegerPropagator() {
	// 	return JaegerPropagator.getInstance();
	// }

	// To have the @Observed support we need to register this aspect
	// @Bean
	// ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
	//     return new ObservedAspect(observationRegistry);
	// }

	// @Bean
	// public OtlpGrpcSpanExporter otlpExporter() {
	// return OtlpGrpcSpanExporter.builder()
	// .setEndpoint(this.otlpProperties.getEndpoint())
	// .setTimeout(this.otlpProperties.getTimeout())
	// .build();
	// }

	// @Bean
	// SpanAspect spanAspect(MethodInvocationProcessor methodInvocationProcessor) {
	// 	return new SpanAspect(methodInvocationProcessor);
	// }

	// @Bean
	// MethodInvocationProcessor methodInvocationProcessor(NewSpanParser newSpanParser,
	// 		Tracer tracer,
	// 		BeanFactory beanFactory) {
	// 	return new ImperativeMethodInvocationProcessor(
	// 			newSpanParser,
	// 			tracer,
	// 			beanFactory::getBean,
	// 			beanFactory::getBean);
	// }

	// @Bean
	// NewSpanParser newSpanParser() {
	// 	return new DefaultNewSpanParser();
	// }

	/**
	 * 24.02.03 version
	 */
	//	@Bean
	//	public NewSpanParser newSpanParser() {
	//		return new DefaultNewSpanParser();
	//	}
	//
	//	@Bean
	//	public MethodInvocationProcessor methodInvocationProcessor(NewSpanParser newSpanParser, Tracer tracer,
	//		BeanFactory beanFactory) {
	//		return new ImperativeMethodInvocationProcessor(newSpanParser, tracer, beanFactory::getBean, beanFactory::getBean);
	//	}
	//
	//	@Bean
	//	public SpanAspect spanAspect(MethodInvocationProcessor methodInvocationProcessor) {
	//		return new SpanAspect(methodInvocationProcessor);
	//	}
	//
	//	@Bean
	//	public ObservationFilter urlObservationFilter() {
	//		return context -> {
	//			if (context.getName().startsWith("spring.security.")) {
	//				Observation.Context root = getRoot(context);
	//				if (root.getName().equals("http.server.requests")) {
	//					context.addHighCardinalityKeyValue(Objects.requireNonNull(root.getHighCardinalityKeyValue("http.url")));
	//				}
	//			}
	//			return context;
	//		};
	//	}
	//
	//	private Observation.Context getRoot(Observation.Context context) {
	//		if (context.getParentObservation() == null) {
	//			return context;
	//		} else {
	//			return getRoot((Observation.Context)context.getParentObservation().getContextView());
	//		}
	//	}
	//
	//	@Bean
	//	public SpanExportingPredicate actuatorSpanExportingPredicate() {
	//		return span -> {
	//			if (span.getTags().get("http.url") != null) {
	//				return !span.getTags().get("http.url").startsWith("/actuator");
	//			}
	//			return true;
	//		};
	//	}

}