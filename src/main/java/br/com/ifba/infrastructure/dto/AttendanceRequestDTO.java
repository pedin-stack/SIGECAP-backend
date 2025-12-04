package br.com.ifba.infrastructure.dto;

import br.com.ifba.infrastructure.role.StatusRole;
import lombok.Data;

@Data
public class AttendanceRequestDTO {
    private Long eventId;   // ID do Evento
    private Long memberId;  // ID do Membro (User)
    private StatusRole status;
    private String justification;
}