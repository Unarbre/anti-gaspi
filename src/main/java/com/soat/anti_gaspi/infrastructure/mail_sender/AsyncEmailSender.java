package com.soat.anti_gaspi.infrastructure.mail_sender;

import com.soat.anti_gaspi.domain.EmailInformation;
import com.soat.anti_gaspi.domain.EmailSender;
import com.soat.anti_gaspi.domain.exception.EnableToSendEmailException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
 // TODO: Rename Sendgrid asyn email sender
public class AsyncEmailSender implements EmailSender {

    @Override // TODO : change to UnableToSendEmailException
    public void send(EmailInformation mail) throws EnableToSendEmailException {
        HttpClient client = HttpClient.newHttpClient();

        // TODO : set http api sengrid to env
        URI uri  = URI.create("https://api.sendgrid.com/v3/mail/send");

        // TODO : replace system out to log
        System.out.println(HttpRequest.BodyPublishers.ofString(mail.getBody()));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-Type", "application/json") // TODO : use class Header instead of string
                .POST(HttpRequest.BodyPublishers.ofString(mail.getBody()))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> System.out.println(response.statusCode()));
    }
}
