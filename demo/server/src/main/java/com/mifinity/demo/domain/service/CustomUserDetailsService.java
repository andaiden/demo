package com.mifinity.demo.domain.service;

import com.mifinity.demo.domain.model.User;
import com.mifinity.demo.domain.port.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by andrea.schembri on 02/09/2017.
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    @Autowired
    public CustomUserDetailsService(final UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * In this method we try to search for a user with a given username. If found we
     * return a {@link UserDetails} class populated with the data retrieved from the database.
     * If no user matches we throw a {@link UsernameNotFoundException}
     *
     * @param username
     * @return {@link UserDetails}
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        try {
            final User user = userDao.loadByUserName(username);

            final List<SimpleGrantedAuthority> collect = user.getAuthorities()
                                                             .stream()
                                                             .map(e -> new SimpleGrantedAuthority(e.getAuthority()))
                                                             .collect(Collectors.toList());

            return new org.springframework.security.core.userdetails.User(user.getUsername(), new String(user.getPassword()), collect);
        } catch (final EntityNotFoundException ex) {
            throw new UsernameNotFoundException(ex.getMessage(), ex);
        }
    }
}
