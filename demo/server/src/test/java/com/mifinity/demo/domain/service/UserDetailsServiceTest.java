package com.mifinity.demo.domain.service;

import com.mifinity.demo.adapter.repositories.user.AuthorityRepository;
import com.mifinity.demo.adapter.repositories.user.UserDaoImpl;
import com.mifinity.demo.adapter.repositories.user.UserRepository;
import com.mifinity.demo.domain.model.Authority;
import com.mifinity.demo.domain.model.User;
import com.mifinity.demo.domain.port.UserDao;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;

/**
 * Created by andrea.schembri on 02/09/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceTest {

    @Mock
    private UserRepository userRepo;

    @Mock
    private AuthorityRepository authRepo;

    private CustomUserDetailsService userDetailsService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        userDetailsService = new CustomUserDetailsService(new UserDaoImpl(userRepo, authRepo));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void when_unableToFindUser_should_throwUserNameNotFoundException() {
        doReturn(Optional.empty()).when(userRepo).findByUsername("username");

        userDetailsService.loadUserByUsername("username");
    }

    @Test
    public void when_userFound_should_returnUserDetailsObject() {
        doReturn(Optional.of(new User("username", "password".toCharArray(), Arrays.asList(new Authority("ADMIN"))))).when(userRepo).findByUsername("username");

        final UserDetails username = userDetailsService.loadUserByUsername("username");

        Assertions.assertThat(username).isNotNull();
        Assertions.assertThat(username.getUsername()).isEqualTo("username");
        Assertions.assertThat(username.getPassword()).isEqualTo("password");
        Assertions.assertThat(username.getAuthorities().size()).isEqualTo(1);
    }
}
