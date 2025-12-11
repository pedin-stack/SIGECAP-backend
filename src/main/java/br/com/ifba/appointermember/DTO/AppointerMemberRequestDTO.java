package br.com.ifba.appointermember.DTO;

import br.com.ifba.infrastructure.role.DeMolayRole;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppointerMemberRequestDTO {

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long userId;

    @NotNull(message = "O ID da nominata (gestão) é obrigatório")
    private Long appointerId;

    @NotNull(message = "O cargo é obrigatório")
    private DeMolayRole role;
}