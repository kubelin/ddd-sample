package com.mypetmanager.global.config;

import java.net.URISyntaxException;
import java.util.Collections;

import javax.annotation.Nullable;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.BasicHttpClientConnectionManager;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpMessage;
import org.apache.hc.core5.http.HttpRequestInterceptor;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.propagation.TextMapGetter;
import io.opentelemetry.context.propagation.TextMapSetter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class HttpConfig {
	// Observability

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
				System.out.println("Headers : ");
				//				System.out.println("Context : {}" Context.current);
				OpenTelemetry otel;
				try {
					otel = OtelConfiguration.initOpenTelemetry();
					log.info("otel? -> \n {}", otel);
					log.info("otel? -> \n {}", otel.getPropagators());
					log.info("getPropagators.getTextMapPropagator? -> \n {}",
						otel.getPropagators().getTextMapPropagator().extract(Context.current(),
							myRequest, HttpServletRequestTextMapGetter.INSTANCE));

					otel.getPropagators().getTextMapPropagator().inject(Context.current(),
						myRequest, HttpServletRequestTextMapSetter.INSTANCE);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				//log.info("\n\nSpan? ->\n {}", Span.current());
				//				observ.getCurrentObservation().getContextView();
				//				Context extractedContext = otel.getPropagators().getTextMapPropagator()
				//					.extract(Context.current(), myRequest, HttpServletRequestTextMapGetter.INSTANCE);

				//				log.info("Headers :\n {}", extractedContext);

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

	public static class HttpServletRequestTextMapSetter implements TextMapSetter<HttpServletRequest> {

		public final static HttpServletRequestTextMapSetter INSTANCE = new HttpServletRequestTextMapSetter();

		@Override
		public void set(@Nullable
		HttpServletRequest carrier, String key, String value) {
			((HttpMessage)carrier).addHeader(key, value);
		}
	}

	@Bean(name = "innerMicroRestTemplate")
	public RestTemplate innerMicroRestTemplate(@Qualifier("innerHttpComponentsClientHttpRequestFactory")
	ClientHttpRequestFactory requestFactory) {
		System.out.println("언제?? innerMicroRestTemplate");
		return new RestTemplate(requestFactory);
	}
}
