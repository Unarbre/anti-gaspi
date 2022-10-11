package com.soat.anti_gaspi.infrastructure.email;

import com.soat.anti_gaspi.infrastructure.email.exceptions.MissingOfferParametersException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.*;
import java.nio.file.Files;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ThymeLeafEmailGeneratorIT {

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private EmailThymeLeafContextFactory emailThymeLeafContextFactory;

    private ThymeLeafEmailGenerator htmlEmailGenerator;

    // TODO remplacer le fichier dans test
    @BeforeEach
    void setup() {
        // TODO authowired ça
        htmlEmailGenerator = new ThymeLeafEmailGenerator(templateEngine, emailThymeLeafContextFactory);
    }
// TODO elaborer le test
    @Test
    void should_process_with_template_file_name_and_context() throws IOException, MissingOfferParametersException {
        var parameters = new OfferConfirmationParameters(
                "a title",
                "a description",
                "an username",
                "an address",
                "01/01/01",
                "02/02/02",
                "http://validation.com",
                "http/deletion.com"
        );
        var result = htmlEmailGenerator.generateEmailFromTemplate(parameters);

        File file = ResourceUtils.getFile("classpath:email-template.fr/confirmation-email-test.html");
        String expected = Files.readString(file.toPath());
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void should_throw_MissingOffer_when_offer_confirmation_is_null() throws IOException, MissingOfferParametersException {
        OfferConfirmationParameters parameters = null;
        var result = htmlEmailGenerator.generateEmailFromTemplate(parameters);

        File file = ResourceUtils.getFile("classpath:email-template.fr/confirmation-email-test.html");
        String expected = Files.readString(file.toPath());
        assertThat(result).isEqualTo(expected);
    }
}