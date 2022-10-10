package com.soat.anti_gaspi.infrastructure.email;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

@Component
public class EmailThymeLeafContextFactoryImpl implements EmailThymeLeafContextFactory {
    @Override
    public Context createEmailTemplateContext(OfferConfirmationParameters offerConfirmationParameters) {
        Context ctx = new Context();

        ctx.setVariable("title", offerConfirmationParameters.getTitle());
        ctx.setVariable("description", offerConfirmationParameters.getDescription());
        ctx.setVariable("validateLink", offerConfirmationParameters.getValidateLink());
        ctx.setVariable("rejectLink", offerConfirmationParameters.getRejectLink());
        return ctx;
    }
}
