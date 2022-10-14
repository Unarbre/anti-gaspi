package com.soat.anti_gaspi.domain.usecases;

import com.soat.anti_gaspi.domain.EmailInformation;
import com.soat.anti_gaspi.domain.EmailSender;
import com.soat.anti_gaspi.domain.exception.UnableToSendEmailException;

public class SendConfirmationMailUsecase {

    private final EmailSender emailSender;

    public SendConfirmationMailUsecase(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void send(EmailInformation mail) throws UnableToSendEmailException {
        emailSender.send(mail);
    }
}
