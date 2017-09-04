package com.mifinity.demo.adapter.rest;

import com.mifinity.demo.adapter.rest.dto.CardDto;
import com.mifinity.demo.domain.model.User;
import com.mifinity.demo.domain.port.CardDao;
import com.mifinity.demo.domain.port.UserDao;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Created by andrea.schembri on 02/09/2017.
 */
@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardDao cardDao;
    private final UserDao userDao;

    @Autowired
    public CardController(final CardDao cardDao, final UserDao userDao) {
        this.cardDao = cardDao;
        this.userDao = userDao;
    }

    @PostMapping
    public CardDto addCard(@Valid @RequestBody final CardDto cardDto,
                           final Authentication authentication) {
        final User user = userDao.loadByUserName(authentication.getName());

        return cardDao.addOrUpdateCard(user, cardDto);
    }

    @GetMapping
    public List<CardDto> getCardList(@RequestParam final String cardNumber,
                                     final Authentication authentication) {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            return cardDao.getAllCardsByFilter(cardNumber);
        } else {
            final User user = userDao.loadByUserName(authentication.getName());
            return cardDao.getAllCardsForUSerByFilter(cardNumber, user);
        }
    }
}
