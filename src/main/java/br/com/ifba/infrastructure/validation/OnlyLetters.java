package br.com.ifba.infrastructure.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OnlyLettersValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OnlyLetters {
    String message() default "O campo deve conter apenas letras";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}