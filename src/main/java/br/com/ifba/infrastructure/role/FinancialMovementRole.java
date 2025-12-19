package br.com.ifba.infrastructure.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FinancialMovementRole {

    INCOMING("Entrada"),
    OUTPUT("Saída");


    private final String label;
}