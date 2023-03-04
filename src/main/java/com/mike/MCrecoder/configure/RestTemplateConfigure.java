package com.mike.MCrecoder.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfigure {

    private SimpleClientHttpRequestFactory getClientHttpRequestFactory() {

        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();

        clientHttpRequestFactory.setConnectTimeout(1000);// 1秒

        return clientHttpRequestFactory;

    }

    @Bean
    public RestTemplate createRestTemplate() {
        return new RestTemplate(getClientHttpRequestFactory());
    }

}
