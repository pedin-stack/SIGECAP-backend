package br.com.ifba.infrastructure.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EventRole {

    SCHEDULED("Agendado"),
    IN_COURSE("Acontencendo"),
    FINISHED("Finalizado"),;

    private final String label;
}