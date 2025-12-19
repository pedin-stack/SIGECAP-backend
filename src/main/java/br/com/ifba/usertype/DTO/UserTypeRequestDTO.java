package br.com.ifba.usertype.DTO;

import br.com.ifba.infrastructure.role.TypeRole;
import br.com.ifba.infrastructure.role.occupationRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserTypeRequestDTO {

    @NotBlank(message = "O nome do tipo de perfil é obrigatório")
    @Size(min = 3, max = 50, message = "O nome do tipo deve ter entre 3 e 50 caracteres")
    // responsavel, dm , maçom senior
    private TypeRole description;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(min = 5, max = 150, message = "A descrição deve ter entre 5 e 150 caracteres")
    // mc,1c,2c...
    private occupationRole occupation;

}