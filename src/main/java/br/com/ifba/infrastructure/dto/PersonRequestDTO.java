package br.com.ifba.infrastructure.dto;

import br.com.ifba.infrastructure.validation.Cellphone;
import br.com.ifba.infrastructure.validation.Cpf;
import br.com.ifba.infrastructure.validation.OnlyLetters;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PersonRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    @OnlyLetters(message = "O nome não deve conter números ou caracteres especiais") // Custom Annotation
    private String name;

    @NotBlank(message = "O CPF é obrigatório")
    @Cpf // Custom Annotation (Valida cálculo matemático)
    private String cpf;

    @NotNull(message = "A data de nascimento é obrigatória")
    @Past(message = "A data de nascimento deve ser no passado") // Validação padrão do Java
    private LocalDate birthDate;

    @NotBlank(message = "O contato é obrigatório")
    @Cellphone // Custom Annotation
    private String contact;

    @NotNull(message = "O ID do endereço é obrigatório")
    @Positive(message = "ID do endereço inválido")
    private Long addressId;
}