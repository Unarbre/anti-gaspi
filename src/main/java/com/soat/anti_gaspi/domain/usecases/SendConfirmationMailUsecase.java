package com.soat.anti_gaspi.domain.usecases;

import com.soat.anti_gaspi.domain.EmailInformation;
import com.soat.anti_gaspi.domain.EmailSender;
import com.soat.anti_gaspi.domain.exception.EnableToSendEmailException;

public class SendConfirmationMailUsecase {

    private final EmailSender emailSender;

    public SendConfirmationMailUsecase(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void send(EmailInformation mail) throws EnableToSendEmailException {
        emailSender.send(mail);
    }
}
