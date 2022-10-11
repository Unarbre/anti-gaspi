package com.soat.anti_gaspi.infrastructure.email;

import javassist.NotFoundException;

public interface EmailGenerator {
    String generateEmailFromTemplate(OfferConfirmationParameters offerConfirmationParameters) throws NotFoundException;
}
