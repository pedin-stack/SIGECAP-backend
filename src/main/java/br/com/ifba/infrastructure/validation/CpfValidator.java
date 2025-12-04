package br.com.ifba.infrastructure.validation;

import br.com.ifba.infrastructure.UTIL.StringUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class CpfValidator implements ConstraintValidator<Cpf, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Padrão Bean Validation: Se for nulo, consideramos válido (deixa pro @NotNull validar se é obrigatório)
        if (value == null) return true;
        return StringUtil.isCpfValido(value);
    }
}