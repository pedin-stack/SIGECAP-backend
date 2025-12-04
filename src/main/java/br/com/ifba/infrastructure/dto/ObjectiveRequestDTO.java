package br.com.ifba.infrastructure.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ObjectiveRequestDTO {
    private String description;
    private double financialGoal;
    private LocalDate deadline;
    private Long creatorId; // ID do Usuário criador
}