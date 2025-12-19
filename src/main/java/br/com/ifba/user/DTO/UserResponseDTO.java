package br.com.ifba.user.DTO;

import br.com.ifba.infrastructure.role.TypeRole;
import br.com.ifba.infrastructure.role.occupationRole;
import lombok.Data;
import java.time.LocalDate;

@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private LocalDate birthDate;
    private boolean active;
    private UserTypeDTO userType;
    private String phone;

    // Classe interna ou DTO separado para o tipo
    @Data
    public static class UserTypeDTO {
        private occupationRole occupation;
        private TypeRole description;
    }

}