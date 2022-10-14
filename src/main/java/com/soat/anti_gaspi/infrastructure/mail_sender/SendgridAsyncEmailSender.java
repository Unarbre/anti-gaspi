package com.soat.anti_gaspi.infrastructure.mail_sender;

import com.soat.anti_gaspi.config.SendgridProperties;
import com.soat.anti_gaspi.domain.EmailInformation;
import com.soat.anti_gaspi.domain.EmailSender;
import com.soat.anti_gaspi.domain.exception.UnableToSendEmailException;
import com.soat.anti_gaspi.infrastructure.exception.SendgridException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@Slf4j
public class SendgridAsyncEmailSender implements EmailSender {
    private final SendgridProperties sendgridProperties;
    private ApplicationContext context;

    public SendgridAsyncEmailSender(SendgridProperties sendgridProperties, ApplicationContext context) {
        this.sendgridProperties = sendgridProperties;
        this.context = context;
    }

    // TODO : put env values in config SendGridConfig ?
    @PostConstruct
    private void postConstruct() {
        if (sendgridProperties.getApiKey() == null || sendgridProperties.getUrl() == null) {
            // TODO log
            throw new SendgridException("Sendgrid key or url is null");
        }
        log.info("SendgridAsyncEmailSender init");
    }

    @Override
    public void send(EmailInformation mail) throws UnableToSendEmailException {
        HttpClient client = HttpClient.newHttpClient();
        log.info("application context == {}", context.getEnvironment().getProperty("CUSTOMCONNSTR_ANGULAR_ENV"));
        log.info("sendgridurl : " + sendgridProperties.getUrl());
        URI uri = URI.create(sendgridProperties.getUrl());
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
