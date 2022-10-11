package com.soat.anti_gaspi.infrastructure.mail_sender;

import com.soat.anti_gaspi.domain.EmailInformation;
import com.soat.anti_gaspi.domain.EmailSender;
import org.springframework.stereotype.Component;

@Component
public class GmailEmailSender implements EmailSender {
    @Override
    public void send(EmailInformation emailInformation) {

    }
}
