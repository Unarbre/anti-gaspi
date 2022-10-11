package com.soat.anti_gaspi.domain.usecases;

import com.soat.anti_gaspi.domain.Mail;
import com.soat.anti_gaspi.domain.MailSender;

public class SendConfirmationMailUsecase {

    private final MailSender mailSender;

    public SendConfirmationMailUsecase(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(Mail mail) {
        mailSender.send(mail);
    }
}
