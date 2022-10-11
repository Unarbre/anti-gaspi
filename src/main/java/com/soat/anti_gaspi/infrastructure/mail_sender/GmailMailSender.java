package com.soat.anti_gaspi.infrastructure.mail_sender;

import com.soat.anti_gaspi.domain.Mail;
import com.soat.anti_gaspi.domain.MailSender;
import org.springframework.stereotype.Component;

@Component
public class GmailMailSender implements MailSender {
    @Override
    public void send(Mail mail) {

    }
}
