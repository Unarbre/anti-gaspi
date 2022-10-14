package com.soat.anti_gaspi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Value("${CUSTOMCONNSTR_REACT_ENV:http://localhost:3000}") // TODO : remove default value
    private String REACT_APP;

    @Value("${CUSTOMCONNSTR_ANGULAR_ENV:http://localhost:4200}") // TODO : remove default value
    private String ANGULAR_APP;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/*").allowedOrigins(REACT_APP, ANGULAR_APP).allowedHeaders("*");
            }
        };
    }
}
