package com.soat.anti_gaspi.infrastructure.email;

import java.util.Objects;

public class OfferConfirmationParameters {
    private final String title;
    private final String description;
    private final String validateLink;
    private final String rejectLink;

    public OfferConfirmationParameters(String title, String description, String validateLink, String rejectLink) {
        this.title = title;
        this.description = description;
        this.validateLink = validateLink;
        this.rejectLink = rejectLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfferConfirmationParameters that = (OfferConfirmationParameters) o;
        return Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(validateLink, that.validateLink) && Objects.equals(rejectLink, that.rejectLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, validateLink, rejectLink);
    }
}
