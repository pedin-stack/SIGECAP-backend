package br.com.ifba.appointermember.DTO;

import br.com.ifba.infrastructure.role.DeMolayRole;
import lombok.Data;

@Data
public class AppointerMemberResponseDTO {
    private Long id;
    private Long userId;
    private String userName;   // Nome do DeMolay

    private Long appointerId;          // ID da Gestão (NOVO)
    private String appointerDescription; // Ex: "Gestão 2025.1" (NOVO - Opcional, mas útil)

    private DeMolayRole role;
}