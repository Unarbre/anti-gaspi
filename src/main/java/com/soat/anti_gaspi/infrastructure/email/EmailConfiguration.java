package com.soat.anti_gaspi.infrastructure.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class EmailConfiguration {

    @Bean
    public SpringTemplateEngine templateEngine() {
        return new SpringTemplateEngine();
    }

    @Bean
    public ClassLoaderTemplateResolver classLoaderTemplateResolver() {
        return new ClassLoaderTemplateResolver();
    }
}
