package com.soat.anti_gaspi.infrastructure.mail_sender;

import com.soat.anti_gaspi.domain.EmailInformation;
import com.soat.anti_gaspi.domain.EmailSender;
import com.soat.anti_gaspi.domain.exception.UnableToSendEmailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@Slf4j
public class SendgridAsyncEmailSender implements EmailSender {

    @Value("${CUSTOMCONNSTR_SENDGRID_KEY}") // TODO : use annotation @PostConstruct to check if value is not null
    private String sendgridKey;

    @Value("${CUSTOMCONNSTR_SENDGRID_URL}") // TODO : use annotation @PostConstruct to check if value is not null
    private String sendgridURL;

    // TODO : put env values in config SendGridConfig ?

    @Override
    public void send(EmailInformation mail) throws UnableToSendEmailException {
        HttpClient client = HttpClient.newHttpClient();

        log.info("sendgridurl : " + sendgridURL);
        URI uri  = URI.create(sendgridURL);
        log.info("mail body : ", HttpRequest.BodyPublishers.ofString(mail.getBody()));
        log.info("SendgridAsyncEmailSender.send() : " + mail);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header(
                        "Content-Type", "application/json") // TODO : use existing constant MimeTypeUtils ?
                .POST(HttpRequest.BodyPublishers.ofString(mail.getBody()))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> System.out.println(response.statusCode()));
    }
}
