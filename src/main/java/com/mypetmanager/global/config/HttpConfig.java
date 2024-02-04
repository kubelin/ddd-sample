package com.mypetmanager.global.config;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.BasicHttpClientConnectionManager;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class HttpConfig {

	@Bean
	public HttpClientConnectionManager httpClientConnectionManager() {
		return new BasicHttpClientConnectionManager();
	}

	@Bean(name = "innerHttpComponentsClientHttpRequestFactory")
	public HttpComponentsClientHttpRequestFactory innerHttpComponentsClientHttpRequestFactory(
		HttpClientConnectionManager connectionManager) {

		RequestConfig requestConfig = RequestConfig.custom()
			.setResponseTimeout(Timeout.ofSeconds(10000)) // 응답 대기 시간
			.setConnectionRequestTimeout(Timeout.ofSeconds(10000)) // connection 대기 시간
			.build();

		HttpClient httpClient = HttpClientBuilder.create()
			.setDefaultRequestConfig(requestConfig)
			.setConnectionManager(connectionManager)
			//			.addRequestInterceptorFirst((HttpRequestInterceptor)(request, entity, context) -> {
			//				System.out.println("Headers : ");
			//				//				log.info("trcacer? -> {}", tracer);
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
			//					// TODO Auto-generated catch block
			//					e.printStackTrace();
			//				}
			//			})
			.build();

		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
		return factory;

	}

	@Bean(name = "innerMicroRestTemplate")
	public RestTemplate innerMicroRestTemplate(@Qualifier("innerHttpComponentsClientHttpRequestFactory")
	ClientHttpRequestFactory requestFactory) {
		System.out.println("언제?? innerMicroRestTemplate");
		return new RestTemplate(requestFactory);
	}
}
