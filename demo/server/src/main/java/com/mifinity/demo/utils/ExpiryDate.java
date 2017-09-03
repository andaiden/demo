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
@Constraint(validatedBy = ExpiryDateConstraint.class)
public @interface ExpiryDate {
    String message() default "Not a valid YY/MM format expiry date";

    // Required by validation runtime
    Class<?>[] groups() default {};

    // Required by validation runtime
    Class<? extends Payload>[] payload() default {};
}
