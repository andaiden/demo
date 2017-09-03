package com.mifinity.demo.domain.port;

import com.mifinity.demo.adapter.rest.dto.CardDto;
import com.mifinity.demo.domain.model.User;

/**
 * Created by andrea.schembri on 02/09/2017.
 */
public interface CardDao {

    /**
     * Adds a new card or updates expiry date if card already exists
     *
     * @param user
     * @param cardDto
     * @return {@link CardDto}
     */
    CardDto addOrUpdateCard(final User user, final CardDto cardDto);

    /**
     * Get card by card number
     *
     * @param cardNumber
     * @return {@link CardDto}
     */
    CardDto getCardByCardNumber(final String cardNumber);

    /**
     * Checks if a card exists given a card number
     *
     * @param cardNumber
     * @return <code>true</code> if card exists, <code>false</code> otherwise
     */
    boolean cardExists(final String cardNumber);
}
