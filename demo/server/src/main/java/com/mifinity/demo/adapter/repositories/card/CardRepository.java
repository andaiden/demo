package com.mifinity.demo.adapter.repositories.card;

import com.mifinity.demo.domain.model.Card;
import com.mifinity.demo.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by andrea.schembri on 02/09/2017.
 */
public interface CardRepository extends JpaRepository<Card, UUID> {

    Optional<Card> findByCardNumber(final String cardNumber);

    @Query("SELECT c FROM Card c WHERE c.cardNumber LIKE :cardNumber%")
    List<Card> getAllCardsByFilter(@Param("cardNumber") final String cardNumber);

    @Query("SELECT c FROM Card c WHERE c.cardNumber LIKE :cardNumber AND c.user = :user")
    List<Card> getAllCardsForUserByFilter(@Param("cardNumber") final String cardNumber,
                                          @Param("user") final User user);
}
