package com.soat.anti_gaspi.infrastructure.email;

import com.soat.anti_gaspi.infrastructure.email.exception.NullOfferConfirmationException;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.ISpringTemplateEngine;

@Component
public class ThymeLeafEmailGenerator implements EmailGenerator {
    private static final String TEMPLATE_FILE_NAME = "confirmation-email-template.html";
    private final ISpringTemplateEngine templateEngine;
    private final EmailThymeLeafContextFactory emailThymeLeafContextFactory;

    public ThymeLeafEmailGenerator(ISpringTemplateEngine templateEngine, EmailThymeLeafContextFactory emailThymeLeafContextFactory) {
        this.templateEngine = templateEngine;
        this.emailThymeLeafContextFactory = emailThymeLeafContextFactory;
    }

    public String generateEmailFromTemplate(OfferConfirmationParameters offerConfirmationParameters) throws NullOfferConfirmationException {
        var ctx = emailThymeLeafContextFactory.createEmailTemplateContext(offerConfirmationParameters);

        return templateEngine.process(TEMPLATE_FILE_NAME, ctx);
    }

}

