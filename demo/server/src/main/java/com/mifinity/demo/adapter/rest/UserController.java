package com.mifinity.demo.adapter.rest;

import com.mifinity.demo.adapter.rest.dto.UserDto;
import com.mifinity.demo.domain.port.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by andrea.schembri on 02/09/2017.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDao userDao;

    @PostMapping
    public UserDto addUser(@Valid @RequestBody final UserDto user) {
        return userDao.adduser(user.getUsername(), user.getPassword());
    }
}
