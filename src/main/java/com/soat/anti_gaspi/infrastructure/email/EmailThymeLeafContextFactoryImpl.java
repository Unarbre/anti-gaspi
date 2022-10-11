package com.soat.anti_gaspi.infrastructure.email;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

@Component
public class EmailThymeLeafContextFactoryImpl implements EmailThymeLeafContextFactory {
    @Override
    public Context createEmailTemplateContext(OfferConfirmationParameters offerConfirmationParameters) {
        Context ctx = new Context();

        ctx.setVariable("title", offerConfirmationParameters.title());
        ctx.setVariable("description", offerConfirmationParameters.description());
        ctx.setVariable("validateLink", offerConfirmationParameters.validateLink());
        ctx.setVariable("rejectLink", offerConfirmationParameters.rejectLink());
        return ctx;
    }
}
