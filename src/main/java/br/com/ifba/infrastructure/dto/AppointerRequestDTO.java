package br.com.ifba.infrastructure.dto;

import br.com.ifba.infrastructure.role.StatusRole;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class AppointerRequestDTO {
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private double financialGoal;
    private StatusRole statusRole;

    // Lista de DTOs de entrada
    private List<AppointerMemberRequestDTO> members;
}