package br.com.ifba.infrastructure.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AttendanceRole {

    PRESENT("Presente"),
    ABSENT("Ausente"),
    JUSTIFIED("Justificado"),;

    private final String label;
}