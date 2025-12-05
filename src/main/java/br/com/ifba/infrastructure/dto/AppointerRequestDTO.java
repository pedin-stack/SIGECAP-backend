package br.com.ifba.infrastructure.dto;

import br.com.ifba.infrastructure.role.StatusRole;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class AppointerRequestDTO {

    @NotBlank(message = "A descrição da gestão é obrigatória")
    @Size(min = 5, max = 100, message = "A descrição deve ter entre 5 e 100 caracteres")
    private String description;

    @NotNull(message = "A data de início é obrigatória")
    private LocalDate startDate;

    @NotNull(message = "A data de término é obrigatória")
    @Future(message = "A data de término deve ser uma data futura")
    private LocalDate endDate;

    @PositiveOrZero(message = "A meta financeira não pode ser negativa")
    private double financialGoal;

    @NotNull(message = "O status da gestão é obrigatório")
    private StatusRole statusRole;

    @NotNull(message = "A lista de membros não pode ser nula")
    @NotEmpty(message = "A nominata deve ter pelo menos um membro oficial")
    @Valid // <---  Valida os itens dentro da lista (AppointerMemberRequestDTO)
    private List<AppointerMemberRequestDTO> members;
}