package br.com.ifba.usertype.DTO;

import br.com.ifba.infrastructure.role.TypeRole;
import br.com.ifba.infrastructure.role.occupationRole;
import lombok.Data;

@Data
public class UserTypeResponseDTO {
    private Long id;
    private occupationRole occupation;
    private TypeRole description;
}