package com.lovefn.grace.tiny.annotation.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IntegerValidatorConstraint.class)
public @interface IntegerValidator {
  boolean require() default false;

  int min() default 0;

  int max() default 2147483647;

  String message() default "{Invalid integer field.}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
