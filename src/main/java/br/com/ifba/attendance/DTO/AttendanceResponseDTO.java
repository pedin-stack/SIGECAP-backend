package br.com.ifba.attendance.DTO;

import br.com.ifba.infrastructure.role.StatusRole;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AttendanceResponseDTO {
    private Long id;

    // Dados do Evento (achatados para facilitar pro Frontend)
    private Long eventId;
    private String eventName;
    private LocalDateTime eventDate;

    // Dados do Membro
    private Long memberId;
    private String memberName;

    // Dados da Presença
    private StatusRole status;
    private String justification;
}