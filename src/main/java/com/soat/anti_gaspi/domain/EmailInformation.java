package com.soat.anti_gaspi.domain;

public class EmailInformation {
// TODO mettre adresse + email
    private final Email receiver;
    private final Email sender;

    private final String mailTitle;

    private final String body;

    public EmailInformation(Email receiver, Email sender, String mailTitle, String body) {
        this.receiver = receiver;
        this.sender = sender;
        this.mailTitle = mailTitle;
        this.body = body;
    }

    public Email getReceiver() {
        return receiver;
    }

    public String getTitle() {
        return mailTitle;
    }

    public String getBody() {
        return body;
    }
}
