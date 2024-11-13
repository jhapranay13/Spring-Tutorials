package com.springrestandcloud.customValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)

public @interface PhoneValid {
    String message() default "Invalid Phone number format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
