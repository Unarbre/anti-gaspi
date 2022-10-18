package com.soat.anti_gaspi.infrastructure.email;

import com.soat.anti_gaspi.infrastructure.email.exception.NullOfferConfirmationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
class ThymeLeafEmailGeneratorIT {

    @Autowired
    private ThymeLeafEmailGenerator htmlEmailGenerator;

    @Test
    void should_process_with_template_file_name_and_context() throws IOException, NullOfferConfirmationException {
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
        // TODO html shall be validated without using a write file. Better use an html parser validator (lib ?)

        File file = ResourceUtils.getFile("classpath:email-template.fr/confirmation-email-test.html");
        String expected = Files.readString(file.toPath());
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void should_throw_MissingOffer_when_offer_confirmation_is_null() {
        assertThatThrownBy(() -> htmlEmailGenerator.generateEmailFromTemplate(null))
                .isExactlyInstanceOf(NullOfferConfirmationException.class);
    }
}