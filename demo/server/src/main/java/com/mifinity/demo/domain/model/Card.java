package com.mifinity.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Created by andrea.schembri on 02/09/2017.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private User user;

    private String cardNumber;

    private String name;

    private String expiryDate;

    public Card(final User user, final String cardNumber, final String name, final String expiryDate) {
        this.user = user;
        this.cardNumber = cardNumber;
        this.name = name;
        this.expiryDate = expiryDate;
    }
}
