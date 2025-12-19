package br.com.ifba.infrastructure.role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MonthsRole {

    JAN("Janeiro"),
    FEV("Fevereiro"),
    MAR("Março"),
    ABR("Abril"),
    MAI("Maio"),
    JUN("Junho"),
    JUL("Julho"),
    AUG("Augusto"),
    SET("Setembro"),
    OUT("Outubro"),
    NOV("Novembro"),
    DEZ("Dezembro");


    private final String label;
}