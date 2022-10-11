package com.soat.anti_gaspi.infrastructure.email;

import com.soat.anti_gaspi.infrastructure.email.exception.NullOfferConfirmationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class EmailThymeLeafContextFactoryImplTest {

    private OfferConfirmationParameters offer = null;

    private OfferConfirmationParameters nullOfferConfirmation = null;


    // TODO On préfère le faire dans chaque classe pour éviter de perdre udtempsde process dans le cas où c'est pas fait.
    @BeforeEach
    public void init() {
        offer = new OfferConfirmationParameters(
                "title1",
                "description1",
                "u",
                "a",
                "01/02/03",
                "01/02/05",
                "https://validation-link.com",
                "https://deletion-link.com"
        );
    }

    @Test
    void should_create_context_with_offer_parameters() throws NullOfferConfirmationException {
        EmailThymeLeafContextFactory emailThymeLeafContextFactory = new EmailThymeLeafContextFactoryImpl();
        OfferConfirmationParameters offerConfirmationParameters = offer;
        var ctx = emailThymeLeafContextFactory.createEmailTemplateContext(offerConfirmationParameters);

        assertThat("title1").isEqualTo(ctx.getVariable("title"));
        assertThat("description1").isEqualTo(ctx.getVariable("description"));
        assertThat("https://validation-link.com").isEqualTo(ctx.getVariable("validateLink"));
        assertThat("https://deletion-link.com").isEqualTo(ctx.getVariable("rejectLink"));
        assertThat(ctx.getVariable("username")).isEqualTo("u");
        assertThat(ctx.getVariable("address")).isEqualTo("a");
        assertThat(ctx.getVariable("availabilityDate")).isEqualTo("01/02/03");
        assertThat(ctx.getVariable("expirationDate")).isEqualTo("01/02/05");
    }

    @Test
    void should_throw_error_on_null_offer(){
        EmailThymeLeafContextFactory emailThymeLeafContextFactory = new EmailThymeLeafContextFactoryImpl();
        offer = null;
        NullOfferConfirmationException nullOfferConfirmationException = Assertions.assertThrows(NullOfferConfirmationException.class, () -> {
            emailThymeLeafContextFactory.createEmailTemplateContext(offer);
        });

        assertThat(nullOfferConfirmationException.getMessage()).isEqualTo("Offer confirmation was null");

    }
}