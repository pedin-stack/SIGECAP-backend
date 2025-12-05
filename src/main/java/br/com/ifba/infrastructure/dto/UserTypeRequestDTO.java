package br.com.ifba.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserTypeRequestDTO {

    @NotBlank(message = "O nome do tipo de perfil é obrigatório")
    @Size(min = 3, max = 50, message = "O nome do tipo deve ter entre 3 e 50 caracteres")
    // Ex: "ADMINISTRADOR", "TESOUREIRO", "MEMBRO"
    private String typeName;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(min = 5, max = 150, message = "A descrição deve ter entre 5 e 150 caracteres")
    // Ex: "Acesso total ao sistema", "Acesso apenas para visualização"
    private String description;
}