package br.com.ifba.event.DTO;

import br.com.ifba.infrastructure.role.EventRole;
import br.com.ifba.infrastructure.role.StatusRole;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EventRequestDTO {

    @NotBlank(message = "O nome do evento é obrigatório")
    @Size(min = 3, max = 150, message = "O nome deve ter entre 3 e 150 caracteres")
    private String name;

    @NotNull(message = "A data principal do evento é obrigatória")
    @FutureOrPresent(message = "A data do evento deve ser a partir de hoje")
    private LocalDateTime date;

    @NotNull(message = "O horário de início é obrigatório")
    @FutureOrPresent(message = "O horário de início deve ser futuro")
    private LocalDateTime startTime;

    @NotNull(message = "O horário de término é obrigatório")
    @FutureOrPresent(message = "O horário de término deve ser futuro")
    private LocalDateTime endTime;

    @NotNull(message = "O status do evento é obrigatório")
    private EventRole eventRole;
}