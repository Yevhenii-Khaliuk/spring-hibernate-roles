package com.khaliuk.controller.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {

    private String fieldOne;
    private String fieldTwo;

    @Override
    public void initialize(PasswordMatch passwordMatch) {
        this.fieldOne = passwordMatch.fieldOne();
        this.fieldTwo = passwordMatch.fieldTwo();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Object fieldOneValue = new BeanWrapperImpl(value).getPropertyValue(fieldOne);
        Object fieldTwoValue = new BeanWrapperImpl(value).getPropertyValue(fieldTwo);
        boolean result = false;
        if (fieldOneValue != null) {
            result = fieldOneValue.equals(fieldTwoValue);
        }
        return result;
    }
}
