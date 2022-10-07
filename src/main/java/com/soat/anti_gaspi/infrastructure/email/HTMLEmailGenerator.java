package com.soat.anti_gaspi.infrastructure.email;

import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Service
public class HTMLEmailGenerator implements EmailGenerator {
    private static final String TEMPLATE_FILE_NAME = "confirmation-email-template.html";
    private final SpringTemplateEngine templateEngine;
    private final ClassLoaderTemplateResolver templateResolver;

    public HTMLEmailGenerator(SpringTemplateEngine templateEngine, ClassLoaderTemplateResolver templateResolver) {
        this.templateEngine = templateEngine;
        this.templateResolver = templateResolver;
    }

    public String generateEmailFromTemplate(OfferConfirmationParameters offerConfirmationParameters) {
        setSpringTemplateEngineWithResolver();

        Context ctx = new Context();

        ctx.setVariable("offerTitle", "title1");
        ctx.setVariable("offerDescription", "description1");
        ctx.setVariable("validateLink", "http://localhost:8080/validate/1");
        ctx.setVariable("rejectLink", "http://localhost:8080/reject/1");

        return templateEngine.process(TEMPLATE_FILE_NAME, ctx);
    }

    private void setSpringTemplateEngineWithResolver() {
        templateResolver.setPrefix("email-templates/fr/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");

        templateResolver.setForceTemplateMode(true);

        templateEngine.setTemplateResolver(templateResolver);
    }
}

