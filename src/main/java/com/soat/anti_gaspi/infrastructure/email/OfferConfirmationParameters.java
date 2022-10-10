package com.soat.anti_gaspi.infrastructure.email;

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


    public String getTitle() {
        return title;
    }

}
