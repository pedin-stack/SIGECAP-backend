package br.com.ifba.appointer.DTO;

import br.com.ifba.appointermember.DTO.AppointerMemberResponseDTO;
import br.com.ifba.infrastructure.role.StatusRole;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class AppointerResponseDTO {
    private Long id;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private double financialGoal;
    private StatusRole statusRole;

    // Lista de DTOs de saída
    private List<AppointerMemberResponseDTO> members;
}