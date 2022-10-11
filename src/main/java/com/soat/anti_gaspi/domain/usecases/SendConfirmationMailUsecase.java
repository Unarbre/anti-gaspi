package com.soat.anti_gaspi.domain.usecases;

import com.soat.anti_gaspi.domain.EmailInformation;
import com.soat.anti_gaspi.domain.EmailSender;

public class SendConfirmationMailUsecase {

    private final EmailSender emailSender;

    public SendConfirmationMailUsecase(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void send(EmailInformation mail) {
        emailSender.send(mail);
    }
}
