package com.soat.anti_gaspi.infrastructure.email;

import org.thymeleaf.context.Context;


public interface EmailThymeLeafContextFactory {
    Context createEmailTemplateContext(OfferConfirmationParameters offerConfirmationParameters);
}
