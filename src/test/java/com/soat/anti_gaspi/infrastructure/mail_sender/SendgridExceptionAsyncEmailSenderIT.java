package com.soat.anti_gaspi.infrastructure.mail_sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.soat.anti_gaspi.domain.Email;
import com.soat.anti_gaspi.domain.EmailInformation;
import com.soat.anti_gaspi.domain.exception.UnableToSendEmailException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
class SendgridExceptionAsyncEmailSenderIT {

    @Autowired
    private SendgridAsyncEmailSender sendgridAsyncEmailSender;


    @Test
    void test() throws UnableToSendEmailException, JsonProcessingException {
        var emailInformation = new EmailInformation(
                Email.builder().value("masataka.ishii@soat.fr").build(),
                Email.builder().value("antigaspi.teamjava@gmail.com").build(),
                "test",
                "<div>test request another yes</div>"
        );
        sendgridAsyncEmailSender.send(emailInformation);
    }
}