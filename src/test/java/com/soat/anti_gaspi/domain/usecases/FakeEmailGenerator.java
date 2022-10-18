package com.soat.anti_gaspi.domain.usecases;

import com.soat.anti_gaspi.infrastructure.email.EmailGenerator;
import com.soat.anti_gaspi.infrastructure.email.OfferConfirmationParameters;
import com.soat.anti_gaspi.infrastructure.email.exception.NullOfferConfirmationException;
import javassist.NotFoundException;

public class FakeEmailGenerator implements EmailGenerator {
    @Override
    public String generateEmailFromTemplate(OfferConfirmationParameters offerConfirmationParameters) throws NotFoundException, NullOfferConfirmationException {
        return "<div>email body generate</div>";
    }
}
