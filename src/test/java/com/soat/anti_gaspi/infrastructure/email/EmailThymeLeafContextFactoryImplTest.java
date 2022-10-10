package com.soat.anti_gaspi.infrastructure.email;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class EmailThymeLeafContextFactoryImplTest {

    @Test
    void should_create_context_with_offer_parameters(){
        EmailThymeLeafContextFactory emailThymeLeafContextFactory = new EmailThymeLeafContextFactoryImpl();
        OfferConfirmationParameters offerConfirmationParameters = new OfferConfirmationParameters(
                "title1",
                "description1",
                "https://validation-link.com",
                "https://deletion-link.com");

        var ctx = emailThymeLeafContextFactory.createEmailTemplateContext(offerConfirmationParameters);

        assertThat("title1").isEqualTo(ctx.getVariable("title"));
        assertThat("description1").isEqualTo(ctx.getVariable("description"));
        assertThat("https://validation-link.com").isEqualTo(ctx.getVariable("validateLink"));
        assertThat("https://deletion-link.com").isEqualTo(ctx.getVariable("rejectLink"));
    }
}