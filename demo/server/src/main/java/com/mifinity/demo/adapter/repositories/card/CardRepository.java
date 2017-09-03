package com.mifinity.demo.adapter.repositories.card;

import com.mifinity.demo.domain.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by andrea.schembri on 02/09/2017.
 */
public interface CardRepository extends JpaRepository<Card, UUID> {

    Optional<Card> findByCardNumber(final String cardNumber);
}
