package com.soat.anti_gaspi.domain;

public class EmailInformation {
// TODO mettre adresse + email
    private final Email receiver;
    private final Email sender;

    public EmailInformation(Email receiver, Email sender) {
        this.receiver = receiver;
        this.sender = sender;
    }
}
