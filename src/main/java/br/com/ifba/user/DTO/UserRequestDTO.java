package br.com.ifba.user.DTO;

import br.com.ifba.infrastructure.validation.Cellphone;
import br.com.ifba.infrastructure.validation.Cpf;
import br.com.ifba.infrastructure.validation.OnlyLetters;
import br.com.ifba.infrastructure.validation.StrongPassword;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class UserRequestDTO {

    // --- DADOS DE PERSON (Herdados na lógica, mas explícitos no DTO) ---

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    @OnlyLetters(message = "O nome não deve conter números ou caracteres especiais") // Custom Annotation
    private String name;

    @NotBlank(message = "O CPF é obrigatório")
    @Cpf // Custom Annotation (Valida matemática do CPF)
    private String cpf;

    @NotNull(message = "A data de nascimento é obrigatória")
    @Past(message = "A data de nascimento deve ser no passado") // Validação do Java para datas
    private LocalDate birthDate;

    @NotBlank(message = "O contato é obrigatório")
    @Cellphone // Custom Annotation (Valida tamanho e dígitos)
    private String contact;

    // --- DADOS DE USER ---

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    @StrongPassword
    private String password;

    private boolean isactive;

    @NotNull(message = "O tipo de usuário é obrigatório")
    private Long userTypeId;

    // Opcional: Se você estiver criando o usuário já com endereço
    private Long addressId;
}