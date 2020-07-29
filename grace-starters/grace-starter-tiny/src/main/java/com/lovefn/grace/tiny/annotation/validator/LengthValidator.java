package com.lovefn.grace.tiny.annotation.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LengthValidatorConstraint.class)
public @interface LengthValidator {

    boolean require() default false;

    int min() default 0;

    int max() default 2147483647;

    String message() default "{com.tencent.fit.risk.ard.annotation.Length.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
