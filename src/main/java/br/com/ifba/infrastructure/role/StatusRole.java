package br.com.ifba.infrastructure.role;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusRole {

    APROVED("Aprovado"),
    REJECTED("Recusado"),
    UNDER_ANALYSIS("Em análisie");

    private final String label;
}

