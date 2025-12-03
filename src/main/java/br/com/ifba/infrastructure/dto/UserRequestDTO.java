package br.com.ifba.infrastructure.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UserRequestDTO {
    // Dados de Person
    private String name;
    private String cpf;
    private LocalDate birthDate;
    private String contact;

    // Dados de User
    private String email;
    private String password; // A senha vem aqui na criação
    private boolean isactive;

    // Para relacionamentos, geralmente passamos apenas o ID
    private Long userTypeId;
}