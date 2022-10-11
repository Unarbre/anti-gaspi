package com.soat.anti_gaspi.infrastructure.email;

import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.ISpringTemplateEngine;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ThymeLeafEmailGeneratorTest {

    private static final String TEMPLATE_FILE_NAME = "confirmation-email-template.html";
    private ThymeLeafEmailGenerator thymeLeafEmailGenerator;

    private OfferConfirmationParameters validOffer = new OfferConfirmationParameters(
            "t",
            "d",
            "u",
            "a",
            "01/02/03",
            "01/02/05",
            "v",
            "r"

    );

    @Mock
    private ISpringTemplateEngine templateEngine;

    @Mock
    private EmailThymeLeafContextFactory emailThymeLeafContextFactory;
    // TODO instancier plutot que mock

    @BeforeEach
    void setup() {
        thymeLeafEmailGenerator = new ThymeLeafEmailGenerator(templateEngine, emailThymeLeafContextFactory);
    }

    @Test
    void should_call_factory() throws NotFoundException {
        OfferConfirmationParameters offerConfirmationParameters = new OfferConfirmationParameters(
                "t",
                "d",
                "u",
                "a",
                "01/02/03",
                "01/02/05",
                "v",
                "r"

        );
        thymeLeafEmailGenerator.generateEmailFromTemplate(offerConfirmationParameters);

        verify(emailThymeLeafContextFactory, times(1)).createEmailTemplateContext(offerConfirmationParameters);
    }

    @Test
    void should_call_engine_process() throws NotFoundException {
        Context ctx = new Context();

        when(emailThymeLeafContextFactory.createEmailTemplateContext(validOffer)).thenReturn(ctx);

        thymeLeafEmailGenerator.generateEmailFromTemplate(validOffer);

        verify(templateEngine, times(1)).process(TEMPLATE_FILE_NAME, ctx);
    }

    @Test
    void should_return_correct_template() throws NotFoundException {
        Context ctx = new Context();
        var expected = "EMAIL BODY";


        when(emailThymeLeafContextFactory.createEmailTemplateContext(validOffer)).thenReturn(ctx);
        when(templateEngine.process(TEMPLATE_FILE_NAME, ctx)).thenReturn(expected);

        var result = thymeLeafEmailGenerator.generateEmailFromTemplate(validOffer);

        assertThat(expected).isEqualTo(result);
    }


}