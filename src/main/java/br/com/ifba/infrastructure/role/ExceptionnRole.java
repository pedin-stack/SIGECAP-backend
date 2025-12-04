package br.com.ifba.infrastructure.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionnRole {

    // Genéricos ---
    RESOURCE_NOT_FOUND("Recurso não encontrado"),
    VALIDATION_ERROR("Erro de validação"),

    // (User) ---
    USER_NOT_FOUND("Usuário não encontrado"),
    USER_NOT_FOUND_EMAIL("Usuário não encontrado com o email informado"),
    USER_ALREADY_EXISTS("Já existe um usuário cadastrado com este email/login"),

    // (Address) ---
    ADDRESS_NOT_FOUND("Endereço não encontrado"),

    // (UserType) ---
    USER_TYPE_NOT_FOUND("Tipo de Usuário não encontrado"),

    // (Person) ---
    PERSON_NOT_FOUND("Pessoa não encontrada"),

    // (Event) ---
    EVENT_NOT_FOUND("Evento não encontrado"),

    //  (Attendance) ---
    ATTENDANCE_NOT_FOUND("Registro de presença não encontrado"),

    //  (FinancialMovement) ---
    FINANCIAL_MOVEMENT_NOT_FOUND("Movimentação financeira não encontrada"),

    // (MonthlyDues) ---
    MONTHLY_DUES_NOT_FOUND("Mensalidade não encontrada"),

    // (Objective) ---
    OBJECTIVE_NOT_FOUND("Objetivo não encontrado"),

    //  (Appointer) ---
    APPOINTER_NOT_FOUND("Nominata (Gestão) não encontrada"),
    APPOINTER_DATE_ERROR("A data de início não pode ser posterior à data de término"),

    // (AppointerMember) ---
    APPOINTER_MEMBER_NOT_FOUND("Vínculo de membro com cargo não encontrado"),
    MEMBER_MANDATORY("É obrigatório informar o Usuário para este cargo"),
    APPOINTER_MANDATORY("É obrigatório informar a Nominata para este cargo"),
    ROLE_MANDATORY("É obrigatório informar qual o cargo");

    private final String message;
}