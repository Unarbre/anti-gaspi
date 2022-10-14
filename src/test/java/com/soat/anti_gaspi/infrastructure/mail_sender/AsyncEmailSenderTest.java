package com.soat.anti_gaspi.infrastructure.mail_sender;

import com.soat.anti_gaspi.domain.Email;
import com.soat.anti_gaspi.domain.EmailInformation;
import com.soat.anti_gaspi.domain.exception.EnableToSendEmailException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AsyncEmailSenderTest {

    private AsyncEmailSender asyncEmailSender = new AsyncEmailSender();

    @Test
    void test() throws EnableToSendEmailException {
        var emailInformation = new EmailInformation(
                Email.builder().value("masatata.ishii@soat.fr").build(),
                Email.builder().value("mohamed.el-barcani@soat.fr").build(),
                "test",
                "<div>test</div>"
        );
        asyncEmailSender.send(emailInformation);
    }
}