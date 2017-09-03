package com.mifinity.demo.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Created by andrea.schembri on 02/09/2017.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Authority {

    @Id
    @GeneratedValue
    private UUID id;

    private String authority;

    public Authority(final String authority) {
        this.authority = authority;
    }
}
