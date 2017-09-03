package com.mifinity.demo.adapter.rest.dto;

import com.mifinity.demo.adapter.repositories.card.CardDaoImpl;
import com.mifinity.demo.adapter.repositories.card.CardRepository;
import com.mifinity.demo.domain.model.Authority;
import com.mifinity.demo.domain.model.Card;
import com.mifinity.demo.domain.model.User;
import com.mifinity.demo.domain.port.CardDao;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by andrea.schembri on 02/09/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class CardDaoTest {

    private static final String VALID_CARD_NUMBER = "1111222233334444";

    @Mock
    private CardRepository cardRepo;

    private CardDao cardDao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        cardDao = new CardDaoImpl(cardRepo);
    }

    @Test
    public void when_cardExists_should_returnTrue() {
        doReturn(Optional.of(new Card(null, VALID_CARD_NUMBER, "name", "19/02"))).when(cardRepo).findByCardNumber(VALID_CARD_NUMBER);

        Assertions.assertThat(cardDao.cardExists(VALID_CARD_NUMBER)).isTrue();
    }

    @Test
    public void when_cardExists_should_returnFalse() {
        doReturn(Optional.empty()).when(cardRepo).findByCardNumber(VALID_CARD_NUMBER);

        Assertions.assertThat(cardDao.cardExists(VALID_CARD_NUMBER)).isFalse();
    }

    @Test
    public void when_getCardByCardNumberFound_should_returnCardDto() {
        final User user = new User("testUser", "password".toCharArray(), Arrays.asList(new Authority("ADMIN")));
        doReturn(Optional.of(new Card(user, VALID_CARD_NUMBER, "Name on Card", "19/02"))).when(cardRepo).findByCardNumber(VALID_CARD_NUMBER);

        final CardDto card = cardDao.getCardByCardNumber(VALID_CARD_NUMBER);

        Assertions.assertThat(card).isNotNull();
        Assertions.assertThat(card.getCardNumber()).isEqualTo(VALID_CARD_NUMBER);
        Assertions.assertThat(card.getName()).isEqualTo("Name on Card");
        Assertions.assertThat(card.getExpiryDate()).isEqualTo("19/02");
    }

    @Test(expected = EntityNotFoundException.class)
    public void when_getCardByCardNumberFound_should_throwEntityNotFoundException() {
        final User user = new User("testUser", "password".toCharArray(), Arrays.asList(new Authority("ADMIN")));
        doReturn(Optional.empty()).when(cardRepo).findByCardNumber(VALID_CARD_NUMBER);

        final CardDto card = cardDao.getCardByCardNumber(VALID_CARD_NUMBER);
    }

    @Test
    public void when_addOrUpdateCard_should_addOrUpdateCardAndReturnAddedOrUpdatedCard() {
        final User user = new User("testUser", "password".toCharArray(), Arrays.asList(new Authority("ADMIN")));
        final Card card = new Card(user, VALID_CARD_NUMBER, "Name on Card", "19/02");
        doReturn(Optional.empty()).when(cardRepo).findByCardNumber(VALID_CARD_NUMBER);

        doReturn(Optional.of(card)).when(cardRepo).findByCardNumber(VALID_CARD_NUMBER);
        doReturn(card).when(cardRepo).saveAndFlush(card);

        final CardDto cardDto = cardDao.addOrUpdateCard(user, CardDto.builder().cardNumber(VALID_CARD_NUMBER).name("Name on Card").expiryDate("19/02").build());

        Assertions.assertThat(cardDto).isNotNull();
        Assertions.assertThat(cardDto.getCardNumber()).isEqualTo(VALID_CARD_NUMBER);
        Assertions.assertThat(cardDto.getName()).isEqualTo("Name on Card");
        Assertions.assertThat(cardDto.getExpiryDate()).isEqualTo("19/02");

        verify(cardRepo, times(1)).saveAndFlush(card);
    }
}
