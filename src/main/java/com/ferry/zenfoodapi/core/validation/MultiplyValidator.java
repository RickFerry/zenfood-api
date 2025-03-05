package com.ferry.zenfoodapi.core.validation;

import javax.validation.ConstraintValidator;

public class MultiplyValidator implements ConstraintValidator<Multiply, Number> {

    private int number;

    @Override
    public void initialize(Multiply constraintAnnotation) {
        this.number = constraintAnnotation.number();
    }

    @Override
    public boolean isValid(Number value, javax.validation.ConstraintValidatorContext context) {
        return value == null || value.intValue() % number == 0;
    }
}
