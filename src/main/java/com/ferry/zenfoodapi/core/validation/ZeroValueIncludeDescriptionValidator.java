package com.ferry.zenfoodapi.core.validation;

import lombok.var;
import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ValidationException;
import java.math.BigDecimal;

public class ZeroValueIncludeDescriptionValidator implements ConstraintValidator<ZeroValueIncludeDescription, Object> {

    private String valueField;
    private String descriptionField;
    private String description;

    @Override
    public void initialize(ZeroValueIncludeDescription constraintAnnotation) {
        this.valueField = constraintAnnotation.valueField();
        this.descriptionField = constraintAnnotation.descriptionField();
        this.description = constraintAnnotation.description();
    }

    @Override
    public boolean isValid(Object object, javax.validation.ConstraintValidatorContext context) {
        boolean valid = true;

        try {
            var valueDescriptor = BeanUtils.getPropertyDescriptor(object.getClass(), valueField);
            var descriptionDescriptor = BeanUtils.getPropertyDescriptor(object.getClass(), descriptionField);

            BigDecimal value = null;
            String text = null;

            if (valueDescriptor != null && valueDescriptor.getReadMethod() != null) {
                Object valueObj = valueDescriptor.getReadMethod().invoke(object);
                if (valueObj instanceof BigDecimal) {
                    value = (BigDecimal) valueObj;
                }
            }

            if (descriptionDescriptor != null && descriptionDescriptor.getReadMethod() != null) {
                Object descriptionObj = descriptionDescriptor.getReadMethod().invoke(object);
                if (descriptionObj instanceof String) {
                    text = (String) descriptionObj;
                }
            }

            if (value != null && BigDecimal.ZERO.compareTo(value) == 0 && text != null) {
                valid = text.toLowerCase().contains(this.description.toLowerCase());
            }

        } catch (Exception e) {
            throw new ValidationException(e);
        }

        return valid;
    }
}
