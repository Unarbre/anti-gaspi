package com.soat.anti_gaspi.infrastructure.email;

public interface EmailGenerator {
    String generateEmailFromTemplate(OfferConfirmationParameters emailParameters);
}
