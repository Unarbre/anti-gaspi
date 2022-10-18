package com.soat.anti_gaspi.domain.usecases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.soat.anti_gaspi.domain.EmailSender;
import com.soat.anti_gaspi.domain.exception.UnableToSendEmailException;
import com.soat.anti_gaspi.infrastructure.email.EmailGenerator;

public class SendConfirmationMailUseCase {

    private final EmailGenerator emailGenerator;
    private final EmailSender emailSender;

    public SendConfirmationMailUseCase(EmailGenerator emailGenerator, EmailSender emailSender) {
        this.emailGenerator = emailGenerator;
        this.emailSender = emailSender;
    }

    public void send(String offerId) throws UnableToSendEmailException, JsonProcessingException {

        //emailSender.send(mail);


    }
}
