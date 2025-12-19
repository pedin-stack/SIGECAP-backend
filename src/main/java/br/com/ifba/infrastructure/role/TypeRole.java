package br.com.ifba.infrastructure.role;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TypeRole {

    DEMOLAY("Demolay"),
    SENIOR("Senior"),
    RESPONSAVEL("Responsavel"),
    MACOM("Macçom");


    private final String label;
}

