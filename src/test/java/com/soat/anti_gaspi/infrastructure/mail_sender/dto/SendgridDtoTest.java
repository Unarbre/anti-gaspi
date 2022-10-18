package com.soat.anti_gaspi.infrastructure.mail_sender.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

class SendgridDtoTest {
    @Test
    void testJackson() throws JsonProcessingException {
        Content content = new Content("text/html", "<div>Hello</div>");
        From from = new From("masataka.ishii@soat.fr");
        To to = new To("mohamed.el-barcani@soat.fr");
        Personalization personalization = new Personalization(List.of(to));
        SendgridDto sendgridDto = new SendgridDto(List.of(personalization), from, "a subject", List.of(content));

        String result = new ObjectMapper().writeValueAsString(sendgridDto);
        System.out.println(result);
    }
}