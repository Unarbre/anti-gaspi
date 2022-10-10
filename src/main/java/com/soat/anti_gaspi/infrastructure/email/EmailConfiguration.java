package com.soat.anti_gaspi.infrastructure.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class EmailConfiguration {

    @Value("${email-template-i18n:fr}")
    String emailLocal;

    @Bean
    public ClassLoaderTemplateResolver classLoaderTemplateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("email-templates/" + emailLocal + "/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");

        templateResolver.setForceTemplateMode(true);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(ClassLoaderTemplateResolver templateResolver) {
        var templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }
}
