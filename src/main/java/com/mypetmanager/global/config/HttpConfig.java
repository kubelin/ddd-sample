package com.mypetmanager.global.config;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

// import io.opentelemetry.api.OpenTelemetry;
// import io.opentelemetry.context.propagation.TextMapGetter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class HttpConfig {
	// Observability
	Long connectionTimeout = 60L;
	Long responseTimeout = 60L;

	@Bean
	public HttpClientConnectionManager httpClientConnectionManager() {
		return PoolingHttpClientConnectionManagerBuilder.create().setMaxConnPerRoute(10).setMaxConnTotal(10).build();
	}

	@Bean(name = "innerHttpComponentsClientHttpRequestFactory")
	public HttpComponentsClientHttpRequestFactory innerHttpComponentsClientHttpRequestFactory(
			HttpClientConnectionManager connectionManager, HttpServletRequest myRequest) {

		RequestConfig requestConfig = RequestConfig.custom()
				.setResponseTimeout(Timeout.ofSeconds(10)) // 응답 대기 시간
				.setConnectionRequestTimeout(Timeout.ofSeconds(10)) // connection 대기 시간
				.build();

		HttpClient httpClient = HttpClientBuilder.create()
				.setDefaultRequestConfig(requestConfig)
				.setConnectionManager(connectionManager)
				.addRequestInterceptorFirst((HttpRequestInterceptor) (request, entity, context) -> {
					log.info("Headers : ");
					Header[] headers = request.getHeaders();

					// OpenTelemetry otel;
					try {
						// otel = OtelConfiguration.initOpenTelemetry();
						// log.info("otel? -> \n {}", otel.getPropagators());
						// log.info("getPropagators.getTextMapPropagator? -> \n {}",
						// otel.getPropagators().getTextMapPropagator().extract(Context.current(),
						// myRequest, HttpServletRequestTextMapGetter.INSTANCE));

						// otel.getPropagators().getTextMapPropagator().inject(Context.current(),
						// myRequest, HttpServletRequestTextMapSetter.INSTANCE);

					} catch (Exception e1) {
						e1.printStackTrace();
					}

					// log.info("\n\nSpan? ->\n {}", Span.current());
					// observ.getCurrentObservation().getContextView();
					// Context extractedContext = otel.getPropagators().getTextMapPropagator()
					// .extract(Context.current(), myRequest,
					// HttpServletRequestTextMapGetter.INSTANCE);

					// log.info("Headers :\n {}", extractedContext);

					// request.addHeader(null);
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

	@Bean(name = "innerMicroRestTemplate")
	public RestTemplate innerMicroRestTemplate(
			@Qualifier("innerHttpComponentsClientHttpRequestFactory") ClientHttpRequestFactory requestFactory) {
		return new RestTemplate(requestFactory);
	}
}