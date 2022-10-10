package com.soat.anti_gaspi.infrastructure.email;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

@Component
public class EmailThymeLeafContextFactoryImpl implements EmailThymeLeafContextFactory {
    @Override
    public Context createEmailTemplateContext(OfferConfirmationParameters offerConfirmationParameters) {
        Context ctx = new Context();

        ctx.setVariable("title", offerConfirmationParameters.getTitle());
        ctx.setVariable("offerDescription", "description1");
        ctx.setVariable("validateLink", "http://localhost:8080/validate/1");
        ctx.setVariable("rejectLink", "http://localhost:8080/reject/1");
        return ctx;
    }
}
