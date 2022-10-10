package com.soat.anti_gaspi.infrastructure.email;

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

    @Mock
    private ISpringTemplateEngine templateEngine;

    @Mock
    private EmailThymeLeafContextFactory emailThymeLeafContextFactory;

    @BeforeEach
    void setup() {
        templateEngine = Mockito.mock(ISpringTemplateEngine.class);
        thymeLeafEmailGenerator = new ThymeLeafEmailGenerator(templateEngine, emailThymeLeafContextFactory);
    }

    @Test
    void should_call_factory() {
        OfferConfirmationParameters offerConfirmationParameters = new OfferConfirmationParameters(
                "t",
                "d",
                "v",
                "r"

        );
        thymeLeafEmailGenerator.generateEmailFromTemplate(offerConfirmationParameters);

        verify(emailThymeLeafContextFactory, times(1)).createEmailTemplateContext(offerConfirmationParameters);
    }

    @Test
    void should_call_engine_process() {
        Context ctx = new Context();

        OfferConfirmationParameters offerConfirmationParameters = new OfferConfirmationParameters(
                "t",
                "d",
                "v",
                "r"
        );
        when(emailThymeLeafContextFactory.createEmailTemplateContext(offerConfirmationParameters)).thenReturn(ctx);

        thymeLeafEmailGenerator.generateEmailFromTemplate(offerConfirmationParameters);

        verify(templateEngine, times(1)).process(TEMPLATE_FILE_NAME, ctx);
    }

    @Test
    void should_return_correct_template(){
        Context ctx = new Context();
        var expected = "EMAIL BODY";

        OfferConfirmationParameters offerConfirmationParameters = new OfferConfirmationParameters(
                "t",
                "d",
                "v",
                "r"
        );
        when(emailThymeLeafContextFactory.createEmailTemplateContext(offerConfirmationParameters)).thenReturn(ctx);
        when(templateEngine.process(TEMPLATE_FILE_NAME, ctx)).thenReturn(expected);

        var result = thymeLeafEmailGenerator.generateEmailFromTemplate(offerConfirmationParameters);

        assertThat(expected).isEqualTo(result);
    }


}