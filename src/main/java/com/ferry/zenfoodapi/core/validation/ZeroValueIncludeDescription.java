package com.ferry.zenfoodapi.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Constraint(validatedBy = {ZeroValueIncludeDescriptionValidator.class})
@Target({TYPE})
public @interface ZeroValueIncludeDescription {
    String message() default "Descrição obrigatória inválida quando o valor é zero";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String valueField();

    String descriptionField();

    String description();
}
