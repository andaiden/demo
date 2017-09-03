package com.mifinity.demo.utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by andrea.schembri on 02/09/2017.
 */
@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueUsernameConstraint.class)
public @interface UniqueUser {
    String message() default "Username is not unique";

    // Required by validation runtime
    Class<?>[] groups() default {};

    // Required by validation runtime
    Class<? extends Payload>[] payload() default {};
}
