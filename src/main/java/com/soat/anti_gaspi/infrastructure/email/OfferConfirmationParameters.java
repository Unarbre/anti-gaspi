package com.soat.anti_gaspi.infrastructure.email;

public record OfferConfirmationParameters(
        String title,
        String description,
        String validateLink,
        String rejectLink) {
}

