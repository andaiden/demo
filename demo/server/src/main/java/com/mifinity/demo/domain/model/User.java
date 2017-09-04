package com.mifinity.demo.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by andrea.schembri on 02/09/2017.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    private String username;

    private char[] password;

    @OneToMany(targetEntity = Authority.class, fetch = FetchType.EAGER)
    private List<Authority> authorities;

    public User(final String username) {
        this.username = username;
    }

    public User(final String username, final char[] password, final List<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }
}
