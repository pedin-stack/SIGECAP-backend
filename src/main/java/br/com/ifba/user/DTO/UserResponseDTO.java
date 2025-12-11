package br.com.ifba.user.DTO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UserResponseDTO {
    private Long id; // Precisamos retornar o ID
    private String name;
    private String email;
    private String cpf;
    private LocalDate birthDate;
    private boolean isactive;

    // No retorno, podemos mandar o nome do tipo em vez do ID, se quisermos
    // O ModelMapper tenta mapear "userType" automaticamente se o nome bater
    private UserTypeDTO userType;

    // Classe interna ou DTO separado para o tipo
    @Data
    public static class UserTypeDTO {
        private String typeName;
        private String description;
    }

    // OBS: Note que NÃO tem o campo 'password' aqui! Segurança total.
}