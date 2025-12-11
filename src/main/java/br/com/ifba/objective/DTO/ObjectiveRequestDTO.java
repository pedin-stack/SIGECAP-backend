package br.com.ifba.objective.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ObjectiveRequestDTO {

    @NotBlank(message = "A descrição do objetivo é obrigatória")
    @Size(min = 5, max = 255, message = "A descrição deve ter entre 5 e 255 caracteres")
    private String description;

    // @Positive garante que não seja zero nem negativo
    @Positive(message = "A meta financeira deve ser maior que zero")
    private double financialGoal;

    @NotNull(message = "A data limite (deadline) é obrigatória")
    // @Future obriga a data ser posterior ao momento atual
    @Future(message = "O prazo limite deve ser uma data futura")
    private LocalDate deadline;

    @NotNull(message = "O ID do criador é obrigatório")
    @Positive(message = "ID do criador inválido")
    private Long creatorId;
}