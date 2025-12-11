package br.com.ifba.attendance.DTO;

import br.com.ifba.infrastructure.role.StatusRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AttendanceRequestDTO {

    @NotNull(message = "O ID do evento é obrigatório")
    @Positive(message = "O ID do evento inválido")
    private Long eventId;

    @NotNull(message = "O ID do membro é obrigatório")
    @Positive(message = "O ID do membro inválido")
    private Long memberId;

    @NotNull(message = "O status da presença é obrigatório")
    private StatusRole status;

    // A justificativa é opcional (não usamos @NotBlank),
    // mas se for enviada, limitamos o tamanho para não estourar o banco.
    @Size(max = 500, message = "A justificativa não pode exceder 500 caracteres")
    private String justification;
}