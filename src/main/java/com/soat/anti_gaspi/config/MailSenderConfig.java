package com.soat.anti_gaspi.config;

import com.soat.anti_gaspi.domain.usecases.SendConfirmationMailUsecase;
import com.soat.anti_gaspi.infrastructure.mail_sender.GmailMailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailSenderConfig {

    @Bean
    SendConfirmationMailUsecase createOfferFactory(GmailMailSender mailSender) {
        return new SendConfirmationMailUsecase(mailSender);
    }
}