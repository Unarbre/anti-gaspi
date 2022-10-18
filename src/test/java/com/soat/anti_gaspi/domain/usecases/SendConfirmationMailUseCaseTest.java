package com.soat.anti_gaspi.domain.usecases;


import com.soat.anti_gaspi.domain.EmailSender;
import com.soat.anti_gaspi.infrastructure.email.EmailGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SendConfirmationMailUseCaseTest {
    private SendConfirmationMailUseCase sendConfirmationMailUseCase;
    EmailGenerator fakeEmailGenerator = new FakeEmailGenerator();

    @Mock
    EmailSender mockEmailSender;

    @BeforeEach
    void setup() {
        sendConfirmationMailUseCase = new SendConfirmationMailUseCase(fakeEmailGenerator, mockEmailSender);
    }

    @Test
    void should_throw_exception_when_offer_not_found_by_id() {

    }
}