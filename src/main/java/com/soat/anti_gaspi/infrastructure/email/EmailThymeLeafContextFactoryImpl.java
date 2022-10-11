package com.soat.anti_gaspi.infrastructure.email;

import javassist.NotFoundException;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

@Component
public class EmailThymeLeafContextFactoryImpl implements EmailThymeLeafContextFactory {
    @Override
    public Context createEmailTemplateContext(OfferConfirmationParameters offerConfirmationParameters) throws NotFoundException {
        if (offerConfirmationParameters == null) {
            throw new NotFoundException("Offer is null");
        }
        Context ctx = new Context();

        ctx.setVariable("title", offerConfirmationParameters.title());
        ctx.setVariable("description", offerConfirmationParameters.description());
        ctx.setVariable("validateLink", offerConfirmationParameters.validateLink());
        ctx.setVariable("rejectLink", offerConfirmationParameters.rejectLink());
        ctx.setVariable("address", offerConfirmationParameters.address());
        ctx.setVariable("username", offerConfirmationParameters.username());
        ctx.setVariable("availabilityDate", offerConfirmationParameters.availabilityDate());
        ctx.setVariable("expirationDate", offerConfirmationParameters.expirationDate());
        return ctx;
    }
}
