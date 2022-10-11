package com.soat.anti_gaspi.infrastructure.email;

import com.soat.anti_gaspi.infrastructure.email.exception.NullOfferConfirmationException;
import org.thymeleaf.context.Context;


public interface EmailThymeLeafContextFactory {
    Context createEmailTemplateContext(OfferConfirmationParameters offerConfirmationParameters) throws NullOfferConfirmationException;
}
