package com.mifinity.demo.adapter.rest;

import com.mifinity.demo.adapter.rest.dto.CardDto;
import com.mifinity.demo.domain.model.User;
import com.mifinity.demo.domain.port.CardDao;
import com.mifinity.demo.domain.port.UserDao;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

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
                           final Principal principal) {
        final User user = userDao.loadByUserName(principal.getName());

        return cardDao.addOrUpdateCard(user, cardDto);
    }

    @GetMapping("/{cardNumber}")
    public CardDto getCard(@PathVariable(name="cardNumber") @CreditCardNumber final String cardNumber) {
        return cardDao.getCardByCardNumber(cardNumber);
    }
}
