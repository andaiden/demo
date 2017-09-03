package com.mifinity.demo.utils;

import com.mifinity.demo.domain.port.UserDao;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by andrea.schembri on 02/09/2017.
 */
@Component
public class ExpiryDateConstraint implements ConstraintValidator<ExpiryDate, String> {

    private final UserDao userDao;

    public ExpiryDateConstraint(final UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void initialize(final ExpiryDate uniqueUser) {}

    @Override
    public boolean isValid(final String expiryDate, final ConstraintValidatorContext constraintValidatorContext) {
        return expiryDate != null && expiryDate.matches("([0-9]{2}\\/(?:0[1-9]|1[0-2]))");
    }
}
