package com.mypetmanager.global.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.tracing.otlp.OtlpProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator;
import io.opentelemetry.context.propagation.ContextPropagators;
import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import io.opentelemetry.semconv.ResourceAttributes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
@EnableConfigurationProperties(OtlpProperties.class)
public class OtelConfiguration {
	@Value("${otlp.tracing.endpoint}")
	public String url;
	//	private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

	//	@Bean
	//			ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
	//				return new ObservedAspect(observationRegistry);
	//			}

	//	@Bean
	//	@ConditionalOnMissingBean(Tracer.class)
	//	public Tracer getTracer() throws Exception {
	//		Tracer tracer;
	//		// 현재의 span 가져오기
	//		OpenTelemetry otel = initOpenTelemetry();
	//		tracer = otel.getTracerProvider().get("mine");
	//
	//		if (tracer == null) {
	//			throw new Exception("ttt");
	//		}
	//
	//		log.warn("Tracer bean is not configured! Switching to " + tracer);
	//		return tracer;
	//	}

	@Bean
	//	OtlpHttpSpanExporter otlpHttpSpanExporter(OtlpProperties property) throws Exception {
	OtlpHttpSpanExporter otlpHttpSpanExporter() throws Exception {
		if (url == null) {
			throw new Exception("Url is null");
		}
		return OtlpHttpSpanExporter.builder()
			//			.setEndpoint(property.getEndpoint())
			.setEndpoint(url)
			.setTimeout(30, TimeUnit.SECONDS)
			.build();
	}

	@Bean
	public OpenTelemetry initOpenTelemetry() throws Exception {
		log.info("!!!!!!!!!!!!!! begin initOpenTelemetry initOpenTelemetry");
		// Export traces to Jaeger over OTLP
		// Span Exporter 생성
		OtlpHttpSpanExporter jaegerOtlpExporter = OtlpHttpSpanExporter.builder().build();

		Resource serviceNameResource = Resource
			.create(Attributes.of(ResourceAttributes.SERVICE_NAME, "otel-custom-jaeger"));

		// Set to process the spans by the Jaeger Exporter
		SdkTracerProvider tracerProvider = SdkTracerProvider.builder()
			//.addSpanProcessor(BatchSpanProcessor.builder(jaegerOtlpExporter).build())
			.addSpanProcessor(SimpleSpanProcessor.create(otlpHttpSpanExporter()))
			.setResource(Resource.getDefault().merge(serviceNameResource))
			.build();
		OpenTelemetrySdk openTelemetry = OpenTelemetrySdk.builder()
			.setTracerProvider(tracerProvider)
			.setPropagators(ContextPropagators.create(W3CTraceContextPropagator.getInstance()))
			.build();

		// it's always a good idea to shut down the SDK cleanly at JVM exit.
		Runtime.getRuntime().addShutdownHook(new Thread(tracerProvider::close));

		return openTelemetry;
	}

	//	@Bean
	//	public MyObservationHandler customObservationHandler(Tracer tracer) {
	//		log.info("!!!!!!!!!!!!!! begin MyObservationHandler customObservationHandler");
	//		return new MyObservationHandler(tracer);
	//	}


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