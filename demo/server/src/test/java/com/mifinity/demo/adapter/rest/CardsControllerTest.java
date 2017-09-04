package com.mifinity.demo.adapter.rest;

import com.mifinity.demo.adapter.rest.dto.CardDto;
import com.mifinity.demo.domain.model.Authority;
import com.mifinity.demo.domain.model.User;
import com.mifinity.demo.domain.port.CardDao;
import com.mifinity.demo.domain.port.UserDao;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by andrea.schembri on 04/09/2017.
 */
@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(CardController.class)
public class CardsControllerTest {

    private static final String CARD_NUMBER = "1111222233334444";

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CardDao cardDao;

    @Mock
    private UserDao userDao;

    @Mock
    private Authentication authentication;

    private CardController cardController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        cardController = new CardController(cardDao, userDao);
    }

    @Test
    public void when_getCardListWithAdminUser_should_returnAllCardsMatchingFilter() {
        final User user = new User("testUser", "password".toCharArray(), singletonList(new Authority("ADMIN")));
        doReturn(Collections.singletonList(new SimpleGrantedAuthority("ADMIN"))).when(authentication).getAuthorities();
        doReturn(Collections.singletonList(CardDto.builder().cardNumber(CARD_NUMBER)
                                                            .name("Name on Card")
                                                            .expiryDate("19/02").build())).when(cardDao).getAllCardsByFilter(CARD_NUMBER);

        final List<CardDto> cardList = cardController.getCardList(CARD_NUMBER, authentication);

        verify(cardDao, times(1)).getAllCardsByFilter(CARD_NUMBER);

        Assertions.assertThat(cardList.size()).isEqualTo(1);
        Assertions.assertThat(cardList.get(0).getCardNumber()).isEqualTo(CARD_NUMBER);
        Assertions.assertThat(cardList.get(0).getName()).isEqualTo("Name on Card");
        Assertions.assertThat(cardList.get(0).getExpiryDate()).isEqualTo("19/02");
    }

    @Test
    public void when_getCardListWithNonAdminUser_should_returnAllCardsMatchingFilterForUserOnly() {
        final User user = new User("testUser", "password".toCharArray(), singletonList(new Authority("ADMIN")));
        doReturn(Collections.singletonList(new SimpleGrantedAuthority("USER"))).when(authentication).getAuthorities();
        doReturn(user).when(userDao).loadByUserName(anyString());
        doReturn(Collections.singletonList(CardDto.builder().cardNumber(CARD_NUMBER)
                                                            .name("Name on Card")
                                                            .expiryDate("19/02").build()))
                .when(cardDao).getAllCardsForUSerByFilter(CARD_NUMBER, user);

        final List<CardDto> cardList = cardController.getCardList(CARD_NUMBER, authentication);

        verify(cardDao, times(1)).getAllCardsForUSerByFilter(CARD_NUMBER, user);

        Assertions.assertThat(cardList.size()).isEqualTo(1);
        Assertions.assertThat(cardList.get(0).getCardNumber()).isEqualTo(CARD_NUMBER);
        Assertions.assertThat(cardList.get(0).getName()).isEqualTo("Name on Card");
        Assertions.assertThat(cardList.get(0).getExpiryDate()).isEqualTo("19/02");
    }
}
