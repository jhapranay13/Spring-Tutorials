package com.example.springRestClient.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Bean
    public WebClient getWebClient(WebClient.Builder builder) {
        return builder.build();
    }
}
