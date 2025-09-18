package com.elhidaja.apiselhidaja.util.validationsPersonalisate;

import java.util.Set;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OptionValidator implements ConstraintValidator<ValidOption, Long>{
    private final Set<Long> validValues = Set.of(0L, 1L, 2L);

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) return false; 
        return validValues.contains(value);
    }
}
