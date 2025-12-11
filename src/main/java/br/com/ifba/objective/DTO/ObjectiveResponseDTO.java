package br.com.ifba.objective.DTO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ObjectiveResponseDTO {
    private Long id;
    private String description;
    private double financialGoal;
    private LocalDate deadline;

    // Dados achatados do Criador
    private Long creatorId;
    private String creatorName;
}