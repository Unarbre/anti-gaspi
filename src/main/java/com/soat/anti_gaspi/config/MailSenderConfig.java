package com.soat.anti_gaspi.config;

import com.soat.anti_gaspi.domain.usecases.SendConfirmationMailUsecase;
import com.soat.anti_gaspi.infrastructure.mail_sender.GmailEmailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



// Supprimer ? Pour le moment le code n'est pas appelé
@Configuration
public class MailSenderConfig {

    @Bean
    SendConfirmationMailUsecase createOfferFactory(GmailEmailSender mailSender) {
        return new SendConfirmationMailUsecase(mailSender);
    }
}