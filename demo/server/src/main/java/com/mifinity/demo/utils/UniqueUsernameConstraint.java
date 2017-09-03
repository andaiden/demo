package com.mifinity.demo.utils;

import com.mifinity.demo.domain.port.UserDao;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by andrea.schembri on 02/09/2017.
 */
@Component
public class UniqueUsernameConstraint implements ConstraintValidator<UniqueUser, String> {

    private final UserDao userDao;

    public UniqueUsernameConstraint(final UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void initialize(final UniqueUser uniqueUser) {}

    @Override
    public boolean isValid(final String username, final ConstraintValidatorContext constraintValidatorContext) {
        return !userDao.userExists(username);
    }
}
