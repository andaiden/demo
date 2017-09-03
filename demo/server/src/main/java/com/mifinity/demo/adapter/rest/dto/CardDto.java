package com.mifinity.demo.adapter.rest.dto;

import com.mifinity.demo.utils.ExpiryDate;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotNull;

/**
 * Created by andrea.schembri on 02/09/2017.
 */
@Getter
@Builder
public class CardDto {

    @NotNull
    @CreditCardNumber
    private String cardNumber;

    @NotNull
    private String name;

    @NotNull
    @ExpiryDate
    private String expiryDate;
}
