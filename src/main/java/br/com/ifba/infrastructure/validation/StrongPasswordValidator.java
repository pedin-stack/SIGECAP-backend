package br.com.ifba.infrastructure.validation;

import br.com.ifba.infrastructure.UTIL.StringUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        // Usa seus métodos utilitários
        return StringUtil.containsNumber(value) && StringUtil.containsSpecialCharacter(value);
    }
}