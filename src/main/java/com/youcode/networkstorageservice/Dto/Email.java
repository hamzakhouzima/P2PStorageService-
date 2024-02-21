package com.youcode.networkstorageservice.Dto;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator; // Import the older version

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = { EmailValidator.class }) // Use the older version of EmailValidator
public @interface Email {

    String message() default "Invalid email format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String regexp() default "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}";
}
