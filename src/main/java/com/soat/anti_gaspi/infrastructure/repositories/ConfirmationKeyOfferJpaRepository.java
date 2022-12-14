package com.soat.anti_gaspi.infrastructure.repositories;

import com.soat.anti_gaspi.infrastructure.model.ConfirmationKeyOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationKeyOfferJpaRepository extends JpaRepository<ConfirmationKeyOfferEntity, Long> {
    Optional<ConfirmationKeyOfferEntity> findByOfferId(String offerId);
    Optional<ConfirmationKeyOfferEntity> findByHash(String keyHash);
}
