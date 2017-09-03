package com.mifinity.demo.domain.port;

import com.mifinity.demo.adapter.rest.dto.UserDto;
import com.mifinity.demo.domain.model.User;

/**
 * Created by andrea.schembri on 02/09/2017.
 */
public interface UserDao {

    /**
     * Add a user to the system
     *
     * @param username
     * @param password
     * @return {@link UserDto}
     */
    UserDto adduser(final String username, final String password);

    /**
     * Find a user given a username
     *
     * @param username
     * @return {@link User}
     */
    User loadByUserName(String username);

    /**
     *
     * @param username
     * @return <code>true</code> if user exists, <code>false</code> otherwise
     */
    boolean userExists(String username);
}
