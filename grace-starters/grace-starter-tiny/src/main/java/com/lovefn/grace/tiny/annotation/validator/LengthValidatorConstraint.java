package com.lovefn.grace.tiny.annotation.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LengthValidatorConstraint implements ConstraintValidator<LengthValidator, Object> {
    private boolean require;
    private int min;
    private int max;
    private String msg;

    @Override
    public void initialize(LengthValidator constraintAnnotation) {
        this.require = constraintAnnotation.require();
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
        this.msg = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (require || null != o) {
            String str = (String) o;
            return (str.length() >= min && str.length() <= max);
        }
        return true;
    }
}
