package com.mifinity.demo.adapter.repositories.user;

import com.mifinity.demo.adapter.rest.dto.UserDto;
import com.mifinity.demo.domain.model.Authority;
import com.mifinity.demo.domain.model.User;
import com.mifinity.demo.domain.port.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;

/**
 * Created by andrea.schembri on 02/09/2017.
 */
@Component
public class UserDaoImpl implements UserDao {

    private final UserRepository repo;

    private final AuthorityRepository authRepo;

    @Autowired
    public UserDaoImpl(final UserRepository repo, final AuthorityRepository authRepo) {
        this.repo = repo;
        this.authRepo = authRepo;
    }

    @Override
    public UserDto adduser(final String username, final String password) {
        final Authority authority = authRepo.findByAuthority("USER").orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find authority of type [%s]", "USER")));

        final User persistedUser = repo.saveAndFlush(new User(username, password.toCharArray(), Arrays.asList(authority)));

        return UserDto.builder().username(persistedUser.getUsername()).password(persistedUser.getPassword().toString()).build();
    }

    @Override
    public User loadByUserName(final String username) {
        return repo.findByUsername(username).orElseThrow(() -> new EntityNotFoundException(String.format("Unable to find user with username [%s]", username)));
    }

    @Override
    public boolean userExists(final String username) {
        return repo.findByUsername(username).isPresent();
    }
}
