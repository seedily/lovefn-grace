package com.lovefn.grace.tiny.annotation.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class IntegerValidatorConstraint implements ConstraintValidator<IntegerValidator, Object> {

    private boolean require;
    private int min;
    private int max;
    private String msg;

    @Override
    public void initialize(IntegerValidator constraintAnnotation) {
        this.require = constraintAnnotation.require();
        this.max = constraintAnnotation.max();
        this.min = constraintAnnotation.min();
        this.msg = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (this.require || null != o) {
            try {
                Integer number = (Integer) o;
                return (this.min <= number && this.max >= number);
            } catch (Exception e) {
                // 如果转换数字失败，则直接返回失败.
                // 处理非数字字符的场景
                log.error("Check number fatal,message is [{}].", e.getMessage());
                return false;
            }
        }
        return true;
    }

}
