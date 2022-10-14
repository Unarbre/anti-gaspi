package com.soat.anti_gaspi.infrastructure.mail_sender;

import com.soat.anti_gaspi.domain.EmailInformation;
import com.soat.anti_gaspi.domain.EmailSender;
import com.soat.anti_gaspi.domain.exception.UnableToSendEmailException;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class GmailEmailSender implements EmailSender {

    private final EmailSenderConfiguration emailSenderConfiguration;

    public GmailEmailSender(EmailSenderConfiguration emailSenderConfiguration) {
        this.emailSenderConfiguration = emailSenderConfiguration;
    }

    @Override
    public void send(EmailInformation emailInformation) throws UnableToSendEmailException {
        MimeMessage message = emailSenderConfiguration.javaMailSender().createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        try {
            helper.setText(emailInformation.getBody(), true);
            helper.setFrom("noreply@baeldung.com");
            helper.setTo(emailInformation.getReceiver().getValue());
            helper.setSubject(emailInformation.getTitle());
            emailSenderConfiguration.javaMailSender().send(message);
        } catch (MessagingException e) {
            throw new UnableToSendEmailException("messaging exception");
        }
    }
}
