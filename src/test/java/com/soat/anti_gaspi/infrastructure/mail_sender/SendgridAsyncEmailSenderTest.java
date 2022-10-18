package com.soat.anti_gaspi.infrastructure.mail_sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.soat.anti_gaspi.config.SendgridProperties;
import com.soat.anti_gaspi.domain.Email;
import com.soat.anti_gaspi.domain.EmailInformation;
import com.soat.anti_gaspi.domain.exception.UnableToSendEmailException;
import com.soat.anti_gaspi.infrastructure.utility.JacksonJsonMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.MimeTypeUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SendgridAsyncEmailSenderTest {

    private SendgridAsyncEmailSender sendgridAsyncEmailSender;

    String url = "http://test.fr";
    String apiKey = "api-key";
    @Mock
    private HttpClient mockHttpClient;

    @Mock
    private CompletableFuture<HttpResponse<String>> mockCompletableFuture;

    @Captor
    private ArgumentCaptor<HttpRequest> httpRequestCaptor;

    @BeforeEach
    void setup() {
        SendgridProperties sendgridProperties = new SendgridProperties();
        sendgridProperties.setUrl(url);
        sendgridProperties.setApiKey(apiKey);

        sendgridAsyncEmailSender = new SendgridAsyncEmailSender(sendgridProperties, mockHttpClient, new JacksonJsonMapper());
    }

    @Test
    void should_send_async_with_request_build_with_content_type_and_authorization_and_body() throws UnableToSendEmailException, JsonProcessingException {
        Email receiverEmail = Email.builder().value("receiver@gmail.com").build();
        Email senderEmail = Email.builder().value("sender@gmail.com").build();
        String title = "A title";
        String body = "<div>body to test</div>";
        EmailInformation emailInformation = new EmailInformation(
                receiverEmail,
                senderEmail,
                title,
                body
        );

        when(mockHttpClient.sendAsync(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString())))
                .thenReturn(mockCompletableFuture);

        sendgridAsyncEmailSender.send(emailInformation);

        verify(mockHttpClient).sendAsync(httpRequestCaptor.capture(), eq(HttpResponse.BodyHandlers.ofString()));

        HttpRequest request = httpRequestCaptor.getValue();

        assertThat(request.uri()).isEqualTo(URI.create(url));
        Map<String, List<String>> headersMap = request.headers().map();
        assertThat(headersMap.isEmpty()).isFalse();

        var contentType = headersMap.get("Content-Type");
        var authorization = headersMap.get("Authorization");
        assertThat(contentType.contains(MimeTypeUtils.APPLICATION_JSON_VALUE)).isTrue();
        assertThat(authorization.contains("Bearer " + apiKey)).isTrue();
    }
}