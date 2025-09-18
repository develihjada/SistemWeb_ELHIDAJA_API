package com.elhidaja.apiselhidaja.util.validationsPersonalisate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = OptionValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface ValidOption {
    String message() default "La opción no es válida solo validos 0 ,1 ,2";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
