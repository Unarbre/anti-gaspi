package com.soat.anti_gaspi.infrastructure.email;

import com.soat.anti_gaspi.infrastructure.email.exception.NullOfferConfirmationException;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

@Component
public class EmailThymeLeafContextFactoryImpl implements EmailThymeLeafContextFactory {
    @Override
    public Context createEmailTemplateContext(OfferConfirmationParameters offerConfirmationParameters) throws NullOfferConfirmationException {
        if (offerConfirmationParameters == null) {
            throw new NullOfferConfirmationException("Offer confirmation was null");
        }

        Context ctx = new Context();

        ctx.setVariable("title", offerConfirmationParameters.title());
        ctx.setVariable("description", offerConfirmationParameters.description());
        ctx.setVariable("hash", offerConfirmationParameters.hash());
        ctx.setVariable("address", offerConfirmationParameters.address());
        ctx.setVariable("username", offerConfirmationParameters.username());
        ctx.setVariable("availabilityDate", offerConfirmationParameters.availabilityDate());
        ctx.setVariable("expirationDate", offerConfirmationParameters.expirationDate());
        return ctx;
    }
}
