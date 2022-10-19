package com.soat.anti_gaspi.infrastructure.email;

public record OfferConfirmationParameters(


        String title,
        String description,
        String username,
        String address,
        String availabilityDate,
        String expirationDate,
        String hash) {
}

