package com.soat.anti_gaspi.infrastructure.email;

import javassist.NotFoundException;
import org.thymeleaf.context.Context;


public interface EmailThymeLeafContextFactory {
    Context createEmailTemplateContext(OfferConfirmationParameters offerConfirmationParameters) throws NotFoundException;
}
