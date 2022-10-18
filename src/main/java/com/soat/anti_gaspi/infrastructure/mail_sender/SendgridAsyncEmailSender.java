package com.soat.anti_gaspi.infrastructure.mail_sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.soat.anti_gaspi.config.SendgridProperties;
import com.soat.anti_gaspi.domain.EmailInformation;
import com.soat.anti_gaspi.domain.EmailSender;
import com.soat.anti_gaspi.domain.exception.UnableToSendEmailException;
import com.soat.anti_gaspi.infrastructure.exception.SendgridException;
import com.soat.anti_gaspi.infrastructure.mail_sender.dto.*;
import com.soat.anti_gaspi.infrastructure.utility.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Component
@Slf4j
public class SendgridAsyncEmailSender implements EmailSender {
    private final SendgridProperties sendgridProperties;
    private final HttpClient client;
    private final JsonMapper jsonMapper;

    public SendgridAsyncEmailSender(SendgridProperties sendgridProperties, HttpClient client, JsonMapper jsonMapper) {
        this.sendgridProperties = sendgridProperties;
        this.client = client;
        this.jsonMapper = jsonMapper;
    }

    @PostConstruct
    private void postConstruct() {
        if (sendgridProperties.getApiKey() == null || sendgridProperties.getUrl() == null) {
            log.error("Sendgrid API key or URL is not set");
            throw new SendgridException("Sendgrid key or url is null");
        }
        log.info("SendgridAsyncEmailSender init");
    }

    @Override
    public void send(EmailInformation mail) throws UnableToSendEmailException, JsonProcessingException {
        HttpRequest request = buildRequest(mail);

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenAccept(stringHttpResponse -> {
            log.info(stringHttpResponse.body());
            // TODO : how to return success response ?
        });
    }

    private HttpRequest buildRequest(EmailInformation mail) throws JsonProcessingException {
        URI uri = URI.create(sendgridProperties.getUrl());

        var content = new Content("text/html", mail.getBody());
        var from = new From(mail.getSender().getValue());
        var to = new To(mail.getReceiver().getValue());
        var perzonalisation = new Personalization(List.of(to));
        var personalizations = List.of(perzonalisation);
        var sendgrid = new SendgridDto(
                personalizations,
                from,
                mail.getTitle(),
                List.of(content)
        );
        var body = jsonMapper.toJson(sendgrid);

        return HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-Type", MimeTypeUtils.APPLICATION_JSON_VALUE)
                .header("Authorization", "Bearer " + sendgridProperties.getApiKey())
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
    }
}
