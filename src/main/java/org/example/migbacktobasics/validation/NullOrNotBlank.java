package org.example.migbacktobasics.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NullOrNotBlankValidator.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface NullOrNotBlank {

    String message() default "Field cannot be blank";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
