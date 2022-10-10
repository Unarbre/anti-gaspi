package com.soat.anti_gaspi.infrastructure.email;

import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class HTMLEmailGenerator implements EmailGenerator {
    private static final String TEMPLATE_FILE_NAME = "confirmation-email-template.html";
    private final SpringTemplateEngine templateEngine;

    public HTMLEmailGenerator(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String generateEmailFromTemplate(OfferConfirmationParameters offerConfirmationParameters) {
        Context ctx = new Context();

        ctx.setVariable("offerTitle", "title1");
        ctx.setVariable("offerDescription", "description1");
        ctx.setVariable("validateLink", "http://localhost:8080/validate/1");
        ctx.setVariable("rejectLink", "http://localhost:8080/reject/1");


        var result = templateEngine.process(TEMPLATE_FILE_NAME, ctx);

        System.out.println(result);
        return result;
    }

}

