package com.mifinity.demo.adapter.rest.dto;

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

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;

/**
 * Created by andrea.schembri on 02/09/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest {

    @Mock
    private UserRepository userRepo;

    @Mock
    private AuthorityRepository authRepo;

    private UserDao userDao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        userDao = new UserDaoImpl(userRepo, authRepo);
    }

    @Test
    public void when_userExists_should_returnTrue() {
        doReturn(Optional.of(new User("username", "password".toCharArray(), Arrays.asList(new Authority("USER"))))).when(userRepo).findByUsername("username");

        Assertions.assertThat(userDao.userExists("username")).isTrue();
    }

    @Test
    public void when_userExists_should_returnFalse() {
        doReturn(Optional.empty()).when(userRepo).findByUsername("username");

        Assertions.assertThat(userDao.userExists("username")).isFalse();
    }

    @Test
    public void when_loadUserByUsernameFound_should_returnUser() {
        doReturn(Optional.of(new User("username", "password".toCharArray(), Arrays.asList(new Authority("USER"))))).when(userRepo).findByUsername("username");

        final User username = userDao.loadByUserName("username");

        Assertions.assertThat(username).isNotNull();
        Assertions.assertThat(username.getUsername()).isEqualTo("username");
        Assertions.assertThat(new String(username.getPassword())).isEqualTo("password");
        Assertions.assertThat(username.getAuthorities().size()).isEqualTo(1);
        Assertions.assertThat(username.getAuthorities().get(0).getAuthority()).isEqualTo("USER");
    }
}
