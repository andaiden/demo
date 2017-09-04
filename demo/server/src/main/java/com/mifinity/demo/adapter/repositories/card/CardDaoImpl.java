package com.mifinity.demo.adapter.repositories.card;

import com.mifinity.demo.adapter.rest.dto.CardDto;
import com.mifinity.demo.domain.model.Card;
import com.mifinity.demo.domain.model.User;
import com.mifinity.demo.domain.port.CardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by andrea.schembri on 02/09/2017.
 */
@Component
public class CardDaoImpl implements CardDao {

    private final CardRepository repo;

    @Autowired
    public CardDaoImpl(final CardRepository repo) {
        this.repo = repo;
    }

    @Override
    public CardDto addOrUpdateCard(final User user, final CardDto cardDto) {
        final Optional<Card> existingCard = repo.findByCardNumber(cardDto.getCardNumber());

        final Card card = getCard(user, cardDto, existingCard);

        final Card persistedCard = repo.saveAndFlush(card);

        return CardDto.builder()
                      .cardNumber(persistedCard.getCardNumber())
                      .name(persistedCard.getName())
                      .expiryDate(persistedCard.getExpiryDate())
                      .build();
    }

    @Override
    public List<CardDto> getAllCardsByFilter(final String cardNumber) {
        final List<Card> cadrDaoList = repo.getAllCardsByFilter(cardNumber);

        return buildDtoList(cadrDaoList);
    }

    @Override
    public List<CardDto> getAllCardsForUSerByFilter(final String cardNumber, final User user) {
        final List<Card> cadrDaoList = repo.getAllCardsForUserByFilter(cardNumber, user);

        return buildDtoList(cadrDaoList);
    }

    @Override
    public boolean cardExists(final String cardNumber) {
        return repo.findByCardNumber(cardNumber).isPresent();
    }

    private Card getCard(final User user, final CardDto cardDto, final Optional<Card> existingCard) {
        Card card;
        if (existingCard.isPresent()) {
            card = existingCard.get();
            card.setExpiryDate(cardDto.getExpiryDate());
        } else {
            card = new Card(user, cardDto.getCardNumber(), cardDto.getName(), cardDto.getExpiryDate());
        }
        return card;
    }

    private List<CardDto> buildDtoList(final List<Card> cadrDaoList) {
        return cadrDaoList.stream().map(e -> CardDto.builder().cardNumber(e.getCardNumber())
                                                              .name(e.getName())
                                                              .expiryDate(e.getExpiryDate())
                                                              .build())
                                                              .collect(Collectors.toList());
    }
}
