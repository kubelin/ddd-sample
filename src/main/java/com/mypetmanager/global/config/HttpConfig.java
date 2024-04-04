package com.mypetmanager.global.config;

import java.util.Collections;
import java.util.Map;

import javax.annotation.Nullable;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.BasicHttpClientConnectionManager;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import io.opentelemetry.context.Context;
import io.opentelemetry.context.ContextKey;
import io.opentelemetry.context.propagation.TextMapGetter;
import io.opentelemetry.context.propagation.TextMapSetter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@AllArgsConstructor
public class HttpConfig {
	// Observability
	//	OpenTelemetry otel;
	//	Context context;
	private static final ContextKey<String> ANIMAL = ContextKey.named("animal");
	private static final ContextKey<Object> BAG = ContextKey.named("bag");

	private static final Context CAT = Context.current().with(ANIMAL, "cat");
	//	private final OpenTelemetry otel;
	//	private final Tracer tracer;

	@Bean
	public HttpClientConnectionManager httpClientConnectionManager() {
		return new BasicHttpClientConnectionManager();
	}

	@Bean(name = "innerHttpComponentsClientHttpRequestFactory")
	public HttpComponentsClientHttpRequestFactory innerHttpComponentsClientHttpRequestFactory(
		HttpClientConnectionManager connectionManager, HttpServletRequest myRequest, HttpServletResponse myResponse) {

		RequestConfig requestConfig = RequestConfig.custom()
			.setResponseTimeout(Timeout.ofSeconds(10000)) // 응답 대기 시간
			.setConnectionRequestTimeout(Timeout.ofSeconds(10000)) // connection 대기 시간
			.build();

		HttpClient httpClient = HttpClientBuilder.create()
			.setDefaultRequestConfig(requestConfig)
			.setConnectionManager(connectionManager)
			//			.addRequestInterceptorFirst((HttpRequestInterceptor)(request, entity, context) -> {
			//
			//				//				System.out.println("Context : {}" Context.current);
			//				try {
			//
			//					//					otel = OtelConfiguration.initOpenTelemetry();
			//					log.info("HttpComponentsClientHttpRequestFactory Context.current() : {}", Context.current());
			//					//					OpenTelemetry myOtels = GlobalOpenTelemetry.get();
			//					//					Tracer trace = myOtels.getTracerProvider().get("mine2");
			//					//					Span mySpan = tracer.spanBuilder(request.getPath()).startSpan();
			//					//					Context contextWith = Context.current().with(mySpan);
			//					//					try (Scope ignored = mySpan.makeCurrent()) {
			//					//						log.info("HttpConfig Scope =>> {} ", ignored);
			//					//						otel.getPropagators().getTextMapPropagator().inject(contextWith, myResponse,
			//					//							HttpServletRequestTextMapSetter.INSTANCE);
			//					//					}
			//					//
			//					//					mySpan.end();
			//
			//					//ExtendedContextPropagators.getTextMapPropagationContext(myOtels.getPropagators()).forEach(request::setRequestProperty);
			//					//					log.info("OpenTelemetry myOtels :\n {}", myOtels.getPropagators().getTextMapPropagator());
			//					//					log.info("OpenTelemetry from controller :\n {}",
			//					//						myOtels.getTracer("mine").spanBuilder("haha").startSpan());
			//
			//					//					SpanContext spanContext = mySpan.getSpanContext();
			//					//					Context myContext = Context.root();
			//
			//					//					myOtels.getPropagators().getTextMapPropagator();
			//					//					//					Span span = tracer.spanBuilder("mine").startSpan().setAttribute("haha", "memememe");
			//					//
			//					//					Context extractedContext = myOtels.getPropagators().getTextMapPropagator()
			//					//						.extract(Context.current(), myRequest, HttpServletRequestTextMapGetter.INSTANCE);
			//					//					//					ContextKey<String> dd = ContextKey.of
			//					//
			//					//					//extractedContext.with(null, null);
			//					//
			//					//					log.info("extractedContext :\n {}", extractedContext);
			//					//
			//					//					//					log.info("extractedContext myRequest :\n {}", extractedContext.get(null));
			//					//					//					log.info("extractedContext myRequest :\n {}", extractedContext.get("parentSpanContext"));
			//					//					log.info("my otel? -> \n {}", otel);
			//					//					log.info("my getPropagators? -> \n {}", otel.getPropagators());
			//					//					log.info("otel? -> \n {}", otel.getPropagators());
			//					//
			//					//					Context myContext = Context.root().with(ANIMAL, "cat");
			//					//
			//					//					log.info("Context.root( :\n {}", myContext);
			//					//
			//					//					otel.getPropagators().getTextMapPropagator().inject(extractedContext,
			//					//						myRequest, HttpServletRequestTextMapSetter.INSTANCE);
			//
			//					//					SpanContext spanContext = mySpan.getSpanContext();
			//					//
			//					//					Context myContext = Context.root();
			//					//
			//					//					ContextKey<String> ANIMAL = ContextKey.named("animal");
			//					//					ContextKey<SpanContext> TEST = ContextKey.named("test");
			//					//					myContext.with(ANIMAL, "value");
			//					//					myContext.with(TEST, spanContext);
			//					//
			//					//					log.info("myContext \n{}", myContext);
			//					//
			//					//					HttpServletRequestTextMapSetter2 requestTextSetter = HttpServletRequestTextMapSetter2.INSTANCE;
			//					//					requestTextSetter.set(request, "Custom-Header", "1111111111");
			//					//					requestTextSetter.set(request, "Dust-Header", "222222222");
			//					//					requestTextSetter.set(request, "traceparent", "00-" + spanContext.getTraceId());
			//
			//					//		Context extractedContext = otel.getPropagators().getTextMapPropagator()
			//					//			.extract(myContext, myRequest, requestTextSetter);
			//
			//					//					log.info("requestTextSetter \n{}", requestTextSetter);
			//					// 1. OtelPropagators로부터 TextMapPropagator 얻기
			//					//					var propagator = myOtels.getPropagators().getTextMapPropagator();
			//					//					var tempPropa = W3CTraceContextPropagator.getInstance();
			//					// 2. HTTP 헤더에 전달할 Carrier 객체 생성
			//
			//					//		var carrier = new HttpHeaderCarrier(request);
			//					//tempPropa.inject(myContext, request, requestTextSetter);
			//					//tempPropa.inject(myContext, request, requestTextSetter);
			//
			//				} catch (Exception e1) {
			//					// TODO Auto-generated catch block
			//					// 예외 처리
			//					log.error("Failed to initialize OpenTelemetry: {}", e1.getMessage(), e1);
			//					e1.printStackTrace();
			//				}
			//
			//				//log.info("\n\nSpan? ->\n {}", Span.current());
			//
			//				Header[] headers = request.getHeaders();
			//				//request.addHeader(null);
			//				log.info("Headers : ");
			//				for (int i = 0; i < headers.length; i++) {
			//					log.info("  {} : {}", headers[i].getName(), headers[i].getValue());
			//				}
			//				log.info("Request Method: {}", request.getMethod());
			//				try {
			//					log.info("URL: {}", request.getUri());
			//				} catch (URISyntaxException e) {
			//					e.printStackTrace();
			//				}
			//			})
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
			System.out.println("HttpServletRequestTextMapSetter set 호출되었냐 = " + key);
			carrier.addHeader(key, value);
			log.info("carrier => {}", carrier.getHeaderNames());
		}
	}

	public static class RestTemplateSetter implements TextMapSetter<RestTemplate> {

		public final static RestTemplateSetter INSTANCE = new RestTemplateSetter();

		public void put(Map<String, String> map, String key, String value) {
			System.out.println("put 호출");
			map.put(key, value);
		}

		@Override
		public void set(@Nullable
		RestTemplate carrier, String key, String value) {
			// TODO Auto-generated method stub
			// HttpHeaders 생성 및 헤더 추가
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Bearer YourAccessToken");
			headers.add("Content-Type", "application/json");

			HttpEntity<String> requestEntity = new HttpEntity<>("Your Request Body", headers);

			carrier.exchange("http://localhost:8089/owners/testM2", HttpMethod.GET, // HTTP 메소드 선택
				requestEntity, // 헤더 및 요청 본문을 포함한 HttpEntity
				String.class // 응답을 어떤 형식으로 받을지 지정
			);
		}
	}

	public static class MyHttpSetter implements TextMapSetter<HttpHeaders> {

		public final static MyHttpSetter INSTANCE = new MyHttpSetter();

		public void put(Map<String, String> map, String key, String value) {
			System.out.println("put 호출");
			map.put(key, value);
		}

		@Override
		public void set(@Nullable
		HttpHeaders carrier, String key, String value) {
			// TODO Auto-generated method stub
			carrier.set(key, value);
		}
	}

	public static class MyTextMapSetter implements TextMapSetter<Map<String, String>> {

		public final static MyTextMapSetter INSTANCE = new MyTextMapSetter();

		public void put(Map<String, String> map, String key, String value) {
			System.out.println("put 호출");
			map.put(key, value);
		}

		@Override
		public void set(@Nullable
		Map<String, String> carrier, String key, String value) {
			System.out.println("set 호출");
			carrier.put(key, value);
			log.info("print {}", carrier);
		}
	}

	public static class MyTextMapGetter implements TextMapGetter<Map<String, String>> {

		public final static MyTextMapGetter INSTANCE = new MyTextMapGetter();

		@Override
		public Iterable<String> keys(Map<String, String> carrier) {
			// TODO Auto-generated method stub
			System.out.println("keys 호출");
			return carrier.keySet();
		}

		@Override
		@Nullable
		public String get(@Nullable
		Map<String, String> carrier, String key) {
			// TODO Auto-generated method stub
			System.out.println("get 호출");
			if (carrier != null) {
				return carrier.get(key);
			}
			return null;
		}
	}

	@Bean(name = "innerMicroRestTemplate")
	public RestTemplate innerMicroRestTemplate(@Qualifier("innerHttpComponentsClientHttpRequestFactory")
	ClientHttpRequestFactory requestFactory) {
		System.out.println("언제?? innerMicroRestTemplate");
		return new RestTemplate(requestFactory);
	}

	//	TextMapGetter<HttpHeaders> getter = new TextMapGetter<HttpHeaders>() {
	//		@Override
	//		public String get(HttpHeaders headers, String s) {
	//			assert headers != null;
	//			return headers.getHeaderString(s);
	//		}
	//
	//		@Override
	//		public Iterable<String> keys(HttpHeaders headers) {
	//			List<String> keys = new ArrayList<>();
	//			MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();
	//			requestHeaders.forEach((k, v) -> {
	//				keys.add(k);
	//			});
	//		}
	//	};

	//	TextMapSetter<HttpURLConnection> setter = new TextMapSetter<HttpURLConnection>() {
	//		@Override
	//		public void set(HttpURLConnection carrier, String key, String value) {
	//			// Insert the context as Header
	//			carrier.setRequestProperty(key, value);
	//		}
	//	};

}
