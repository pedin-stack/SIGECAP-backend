package br.com.ifba.infrastructure.validation;

import br.com.ifba.infrastructure.UTIL.StringUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BrazilDateValidator implements ConstraintValidator<BrazilDate, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return StringUtil.isValidData(value);
    }
}