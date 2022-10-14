package com.soat.anti_gaspi.infrastructure.mail_sender;

import com.sendgrid.*;
import com.soat.anti_gaspi.domain.EmailInformation;
import com.soat.anti_gaspi.domain.EmailSender;
import com.soat.anti_gaspi.domain.exception.EnableToSendEmailException;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Component
public class SendGridEmailSender implements EmailSender {

    private final EmailSenderConfiguration emailSenderConfiguration;

    public SendGridEmailSender(EmailSenderConfiguration emailSenderConfiguration) {
        this.emailSenderConfiguration = emailSenderConfiguration;
    }

    @Override
    public void send(EmailInformation emailInformation) throws EnableToSendEmailException {
        SendGrid sendGridClient = emailSenderConfiguration.sendGridMailSender();

        Email from = new Email(emailInformation.getSender().getValue());
        Email to = new Email(emailInformation.getReceiver().getValue());
        Content content = new Content("text/plain", emailInformation.getBody());
        Mail mail = new Mail(from, emailInformation.getTitle(), to, content);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGridClient.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw new EnableToSendEmailException("messaging exception");
        }
    }
}
