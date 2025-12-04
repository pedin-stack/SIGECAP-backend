package br.com.ifba.infrastructure.dto;

import br.com.ifba.infrastructure.role.DeMolayRole;
import lombok.Data;

@Data
public class AppointerMemberRequestDTO {
    private Long userId;       // Quem é
    private Long appointerId;  // A qual gestão pertence (NOVO)
    private DeMolayRole role;  // Qual o cargo
}