package com.mypetmanager.global.config;

import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectionPoolConfig{

    @Bean
    public HttpClientConnectionManager poolingHttpClientConnectionManager(){
        return PoolingHttpClientConnectionManagerBuilder.create()
                .setMaxConnPerRoute(10)
                .setMaxConnTotal(30)
                .build();
    }
}