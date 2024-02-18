package com.mypetmanager.global.config;

import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.BasicHttpClientConnectionManager;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpRequestInterceptor;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanContext;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.ContextKey;
import io.opentelemetry.context.propagation.TextMapGetter;
import io.opentelemetry.context.propagation.TextMapSetter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class HttpConfig {
	// Observability
	//	OpenTelemetry otel;
	//	Context context;
	private static final ContextKey<String> ANIMAL = ContextKey.named("animal");
	private static final ContextKey<Object> BAG = ContextKey.named("bag");

	private static final Context CAT = Context.current().with(ANIMAL, "cat");

	@Bean
	public HttpClientConnectionManager httpClientConnectionManager() {
		return new BasicHttpClientConnectionManager();
	}

	@Bean(name = "innerHttpComponentsClientHttpRequestFactory")
	public HttpComponentsClientHttpRequestFactory innerHttpComponentsClientHttpRequestFactory(
		HttpClientConnectionManager connectionManager, HttpServletRequest myRequest) {

		RequestConfig requestConfig = RequestConfig.custom()
			.setResponseTimeout(Timeout.ofSeconds(10000)) // 응답 대기 시간
			.setConnectionRequestTimeout(Timeout.ofSeconds(10000)) // connection 대기 시간
			.build();

		HttpClient httpClient = HttpClientBuilder.create()
			.setDefaultRequestConfig(requestConfig)
			.setConnectionManager(connectionManager)
			.addRequestInterceptorFirst((HttpRequestInterceptor)(request, entity, context) -> {
				log.info("Context.current() : {}", Context.current());
				//				System.out.println("Context : {}" Context.current);
				try {

					//					otel = OtelConfiguration.initOpenTelemetry();
					OpenTelemetry myOtels = GlobalOpenTelemetry.get();
					Tracer trace = myOtels.getTracerProvider().get("mine");
					Span mySpan = trace.spanBuilder("haha").startSpan();

					log.info("myOtels.myOtels() : {}", myOtels.getTracerProvider());
					//ExtendedContextPropagators.getTextMapPropagationContext(myOtels.getPropagators()).forEach(request::setRequestProperty);
					//					log.info("OpenTelemetry myOtels :\n {}", myOtels.getPropagators().getTextMapPropagator());
					//					log.info("OpenTelemetry from controller :\n {}",
					//						myOtels.getTracer("mine").spanBuilder("haha").startSpan());

					//					SpanContext spanContext = mySpan.getSpanContext();
					//					Context myContext = Context.root();

					//					myOtels.getPropagators().getTextMapPropagator();
					//					//					Span span = tracer.spanBuilder("mine").startSpan().setAttribute("haha", "memememe");
					//
					//					Context extractedContext = myOtels.getPropagators().getTextMapPropagator()
					//						.extract(Context.current(), myRequest, HttpServletRequestTextMapGetter.INSTANCE);
					//					//					ContextKey<String> dd = ContextKey.of
					//
					//					//extractedContext.with(null, null);
					//
					//					log.info("extractedContext :\n {}", extractedContext);
					//
					//					//					log.info("extractedContext myRequest :\n {}", extractedContext.get(null));
					//					//					log.info("extractedContext myRequest :\n {}", extractedContext.get("parentSpanContext"));
					//					log.info("my otel? -> \n {}", otel);
					//					log.info("my getPropagators? -> \n {}", otel.getPropagators());
					//					log.info("otel? -> \n {}", otel.getPropagators());
					//
					//					Context myContext = Context.root().with(ANIMAL, "cat");
					//
					//					log.info("Context.root( :\n {}", myContext);
					//
					//					otel.getPropagators().getTextMapPropagator().inject(extractedContext,
					//						myRequest, HttpServletRequestTextMapSetter.INSTANCE);
					SpanContext spanContext = mySpan.getSpanContext();

					Context myContext = Context.root();

					ContextKey<String> ANIMAL = ContextKey.named("animal");
					ContextKey<SpanContext> TEST = ContextKey.named("test");
					myContext.with(ANIMAL, "value");
					myContext.with(TEST, spanContext);

					log.info("myContext \n{}", myContext);

					HttpServletRequestTextMapSetter2 requestTextSetter = HttpServletRequestTextMapSetter2.INSTANCE;
					requestTextSetter.set(request, "Custom-Header", "1111111111");
					requestTextSetter.set(request, "Dust-Header", "222222222");

					//		Context extractedContext = otel.getPropagators().getTextMapPropagator()
					//			.extract(myContext, myRequest, requestTextSetter);

					log.info("requestTextSetter \n{}", requestTextSetter);
					// 1. OtelPropagators로부터 TextMapPropagator 얻기
					var propagator = myOtels.getPropagators().getTextMapPropagator();
					var tempPropa = W3CTraceContextPropagator.getInstance();
					// 2. HTTP 헤더에 전달할 Carrier 객체 생성

					Map<String, String> carrier = new HashMap<>();
					carrier.put("why", "donttno");
					carrier.put("Rollback", "Rollback");

					//		var carrier = new HttpHeaderCarrier(request);
					tempPropa.inject(myContext, request, requestTextSetter);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					// 예외 처리
					log.error("Failed to initialize OpenTelemetry: {}", e1.getMessage(), e1);
					e1.printStackTrace();
				}

				//log.info("\n\nSpan? ->\n {}", Span.current());

				Header[] headers = request.getHeaders();
				//request.addHeader(null);
				log.info("Headers : ");
				for (int i = 0; i < headers.length; i++) {
					log.info("  {} : {}", headers[i].getName(), headers[i].getValue());
				}
				log.info("Request Method: {}", request.getMethod());
				try {
					log.info("URL: {}", request.getUri());
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			})
			.build();

		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
		return factory;

	}

	public static class HttpServletRequestTextMapGetter implements TextMapGetter<HttpServletRequest> {

		public final static HttpServletRequestTextMapGetter INSTANCE = new HttpServletRequestTextMapGetter();

		@Override
		public Iterable<String> keys(HttpServletRequest carrier) {
			return Collections.list(carrier.getHeaderNames());
		}

		@Nullable
		@Override
		public String get(@Nullable
		HttpServletRequest carrier, String key) {
			return carrier.getHeader(key);
		}
	}

	public static class HttpServletRequestTextMapSetter2 implements TextMapSetter<HttpRequest> {

		public final static HttpServletRequestTextMapSetter2 INSTANCE = new HttpServletRequestTextMapSetter2();

		@Override
		public void set(@Nullable
		HttpRequest carrier, String key, String value) {
			// TODO Auto-generated method stub
			System.out.println("set 호출되었냐 = " + key);
			carrier.addHeader(key, value);
		}

	}

	public static class HttpServletRequestTextMapSetter implements TextMapSetter<HttpServletResponse> {

		public final static HttpServletRequestTextMapSetter INSTANCE = new HttpServletRequestTextMapSetter();


		@Override
		public void set(@Nullable
		HttpServletResponse carrier, String key, String value) {
			// TODO Auto-generated method stub
			System.out.println("set 호출되었냐 = " + key);
			carrier.addHeader(key, value);
		}
	}

	@Bean(name = "innerMicroRestTemplate")
	public RestTemplate innerMicroRestTemplate(@Qualifier("innerHttpComponentsClientHttpRequestFactory")
	ClientHttpRequestFactory requestFactory) {
		System.out.println("언제?? innerMicroRestTemplate");
		return new RestTemplate(requestFactory);
	}
}
