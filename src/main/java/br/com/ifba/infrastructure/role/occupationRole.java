package br.com.ifba.infrastructure.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum occupationRole {

    // --- TRÍADE ---
    MASTER_COUNCILOR("Mestre Conselheiro"),
    FIRST_COUNCILOR("Primeiro Conselheiro"),  // No diagrama está 'firstCouncilor'
    SECOND_COUNCILOR("Segundo Conselheiro"), // No diagrama está 'secondCouncilor'

    // --- DIRETORIA --
    SCRIBE("Escrivão"),
    TREASURER("Tesoureiro"),
    ORATOR("Orador"),
    CHAPLAIN("Capelão"),
    MARSHAL("Mestre de Cerimônias"),
    HOSPITALER("Hospitaleiro"),

    // --- Diáconos e Mordomos ---
    FIRST_DEACON("Primeiro Diácono"),
    SECOND_DEACON("Segundo Diácono"),
    FIRST_STEWARD("Primeiro Mordomo"),
    SECOND_STEWARD("Segundo Mordomo"),

    // --- Outros Oficiais ---
    STANDARD_BEARER("Porta Bandeira"),
    SENTINEL("Sentinela"),
    ORGANIST("Organista"),

    // --- Preceptores ---
    FIRST_PRECEPTOR("1º Preceptor"),
    SECOND_PRECEPTOR("2º Preceptor"),
    THIRD_PRECEPTOR("3º Preceptor"),
    FOURTH_PRECEPTOR("4º Preceptor"),
    FIFTH_PRECEPTOR("5º Preceptor"),
    SIXTH_PRECEPTOR("6º Preceptor"),
    SEVENTH_PRECEPTOR("7º Preceptor"),

    ADVISORY_BOARD_PRESIDENT("Presidente do conselho consultivi");

    private final String label;
}
