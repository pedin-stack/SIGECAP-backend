package br.com.ifba.address.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import br.com.ifba.infrastructure.role.ExceptionnRole;

@Data
public class AddressRequestDTO {

    private final ExceptionnRole role;

    @NotBlank(message = "A rua é obrigatória")
    @Size(min = 3, max = 150, message = "A rua deve ter entre 3 e 150 caracteres")
    private String street;

    @NotBlank(message = "O número é obrigatório")
    @Size(max = 10, message = "O número deve ter no máximo 10 caracteres")
    private String number;

    @NotBlank(message = "O bairro é obrigatório")
    @Size(min = 3, max = 100, message = "O bairro deve ter entre 3 e 100 caracteres")
    private String neighborhood;

    @NotBlank(message = "A cidade é obrigatória")
    @Size(min = 3, max = 100, message = "A cidade deve ter entre 3 e 100 caracteres")
    private String city;

    @NotBlank(message = "O estado é obrigatório")
    @Size(min = 2, max = 2, message = "O estado deve ser a sigla (Ex: BA, SP)")
    private String state;

    @NotBlank(message = "O CEP é obrigatório")
    // Regex: Aceita "12345-678" ou "12345678"
    @Pattern(regexp = "\\d{5}-?\\d{3}", message = "O CEP deve estar no formato 00000-000 ou conter apenas 8 números")
    private String cep;
}