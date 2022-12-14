package com.soat.anti_gaspi.application.controller.offer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.soat.anti_gaspi.application.OfferMapper;
import com.soat.anti_gaspi.application.controller.offer.dto.*;
import com.soat.anti_gaspi.domain.Offer;
import com.soat.anti_gaspi.domain.OfferId;
import com.soat.anti_gaspi.domain.exception.UnableToSendEmailException;
import com.soat.anti_gaspi.domain.usecases.*;
import com.soat.anti_gaspi.infrastructure.email.exception.NullOfferConfirmationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(OfferController.PATH)
@AllArgsConstructor
public class OfferController {
    public static final String PATH = "/api/offers";
    private final CreateOfferUseCase createOffer;
    private final GetOfferUseCase getOffer;
    private final GetPublishedOffersUseCase getPublishedOffers;
    private final PublishOfferUseCase publishOfferUsecase;
    private final DeleteOfferUsecase deleteOfferUsecase;
    private final SendConfirmationMailUseCase sendConfirmationMail;
    private final OfferMapper offerMapper = new OfferMapper();

    @Autowired
    private final Environment environment;


    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Validated OfferDto offerDto) throws NullOfferConfirmationException, UnableToSendEmailException, JsonProcessingException {
        var offer = offerMapper.map(offerDto);

        var offerId = createOffer.create(offer);

        sendConfirmationMail.send(new OfferId(offerId));

        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(offerId)
                        .toUri()
        ).build();
    }

    @GetMapping
    public ResponseEntity<OfferPage> getPaginatedPublishedOffers(@RequestParam int pageNumber,
                                                                 @RequestParam int pageSize,
                                                                 @RequestParam String sortBy,
                                                                 @RequestParam(defaultValue = "asc") String sortOrder) {

        List<Offer> allOffers = getPublishedOffers.get();
        var result = PaginatedOffersMapper.map(allOffers, pageNumber, pageSize, sortBy, sortOrder);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SavedOfferDto> findById(@PathVariable("id") String id) {

        var offerId = new OfferId(id);
        return getOffer.get(offerId).map(OfferDtoMapper::map)
                .map(offer -> new ResponseEntity<>(offer, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/validate")
    public ModelAndView validateOffer(@RequestParam("hash") final String hash) {
        final String frontUrl = environment.getProperty("front-url");
        publishOfferUsecase.publish(hash);
        return new ModelAndView("redirect:" + frontUrl + "/validated");
    }

    @GetMapping("/delete")
    public ModelAndView deleteOffer(@RequestParam("hash") String hash) {
        final String frontUrl = environment.getProperty("front-url");
        deleteOfferUsecase.delete(hash);
        return new ModelAndView("redirect:" + frontUrl + "/deleted");
    }
}
