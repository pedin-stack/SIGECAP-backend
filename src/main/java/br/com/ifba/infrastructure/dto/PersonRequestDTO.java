package br.com.ifba.infrastructure.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PersonRequestDTO {
    private String name;
    private String cpf;
    private LocalDate birthDate;
    private String contact;
    private Long addressId; // ID do Endereço (Linkando um endereço existente)
}