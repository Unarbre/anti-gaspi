package com.soat.anti_gaspi.infrastructure.mail_sender;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;


@Configuration
public class EmailSenderConfiguration {

    @Value("${senGrid.send-grid-api-key}")
    String sendGridApiKey;

    @Bean
    public JavaMailSender javaMailSender() {
        // TODO variabiliser / mettre en spring property
        
        var mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("my.gmail@gmail.com");
        mailSender.setPassword("password");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public SendGrid sendGridMailSender() {
        SendGrid sendGridClient = new SendGrid(sendGridApiKey);

        return sendGridClient;
    }
}
