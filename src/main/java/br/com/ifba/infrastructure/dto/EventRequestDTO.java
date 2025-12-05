package br.com.ifba.infrastructure.dto;

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

    @NotBlank(message = "O local do evento é obrigatório")
    @Size(min = 3, max = 200, message = "O local deve ter entre 3 e 200 caracteres")
    private String local;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 1000, message = "A descrição não pode exceder 1000 caracteres")
    private String description;

    @NotNull(message = "O horário de início é obrigatório")
    @FutureOrPresent(message = "O horário de início deve ser futuro")
    private LocalDateTime startTime;

    @NotNull(message = "O horário de término é obrigatório")
    @FutureOrPresent(message = "O horário de término deve ser futuro")
    private LocalDateTime endTime;

    @NotNull(message = "O status do evento é obrigatório")
    private StatusRole statusRole;

    @NotBlank(message = "A imagem do evento é obrigatória")
    private String eventPics;
}