package com.soat.anti_gaspi.infrastructure.mail_sender;

import com.soat.anti_gaspi.config.SendgridProperties;
import com.soat.anti_gaspi.domain.EmailInformation;
import com.soat.anti_gaspi.domain.EmailSender;
import com.soat.anti_gaspi.domain.exception.UnableToSendEmailException;
import com.soat.anti_gaspi.infrastructure.exception.SendgridException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@Slf4j
public class SendgridAsyncEmailSender implements EmailSender {
    private final SendgridProperties sendgridProperties;

    public SendgridAsyncEmailSender(SendgridProperties sendgridProperties) {
        this.sendgridProperties = sendgridProperties;
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
    public void send(EmailInformation mail) throws UnableToSendEmailException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = buildRequest(mail);

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> System.out.println(response.statusCode()));
    }

    private HttpRequest buildRequest(EmailInformation mail) {
        URI uri = URI.create(sendgridProperties.getUrl());

        return HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-type", MimeTypeUtils.APPLICATION_JSON_VALUE)
                .header("Authorization", "Bearer " + sendgridProperties.getApiKey())
                .POST(HttpRequest.BodyPublishers.ofString("{\"personalizations\": [{\"to\": [{\"email\": \"masataka.ishii@soat.fr\"}]}],\"from\": {\"email\": \"mohamed.el-barcani@soat.fr\"},\"subject\": \"Hello, World!\",\"content\": [{\"type\": \"text/html\", \"value\": \"Heya!\"}]"))
                .build(); // TODO : create sendgrid email dto to build body of email
                // TODO : use jackson object mapper to convert dto to json
    }
}
