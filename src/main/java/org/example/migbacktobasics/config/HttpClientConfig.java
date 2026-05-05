package org.example.migbacktobasics.config;

import org.example.migbacktobasics.properties.HttpClientProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "mig", name = "repository-type", havingValue = "http")
@EnableConfigurationProperties(HttpClientProperties.class)
public class HttpClientConfig {

    @Bean
    public HttpClient httpClient(HttpClientProperties properties) {
        return new HttpClient(properties.url(), properties.username(), properties.password());
    }

    public record HttpClient(String url, String username, String password) {
    }

}
