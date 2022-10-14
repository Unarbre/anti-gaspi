package com.soat.anti_gaspi.infrastructure.mail_sender;

import com.soat.anti_gaspi.domain.Email;
import com.soat.anti_gaspi.domain.EmailInformation;
import com.soat.anti_gaspi.domain.exception.UnableToSendEmailException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SendgridAsyncEmailSenderTest {

    @Autowired
    private SendgridAsyncEmailSender sendgridAsyncEmailSender;
    // TODO : to test mail send, use wiremock
    @Test
    void test() throws UnableToSendEmailException {
        var emailInformation = new EmailInformation(
                Email.builder().value("masatata.ishii@soat.fr").build(),
                Email.builder().value("mohamed.el-barcani@soat.fr").build(),
                "test",
                "<div>test</div>"
        );
        sendgridAsyncEmailSender.send(emailInformation);

    }
}