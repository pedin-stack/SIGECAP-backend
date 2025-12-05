package br.com.ifba.infrastructure.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class MonthlyDuesRequestDTO {

    @Positive(message = "O valor da mensalidade deve ser maior que zero")
    private double value;

    // Garante que o mês seja válido (Janeiro a Dezembro)
    @Min(value = 1, message = "O mês de referência deve ser entre 1 e 12")
    @Max(value = 12, message = "O mês de referência deve ser entre 1 e 12")
    private int referenceMonth;

    // Evita anos absurdos (ex: ano 0 ou ano 10)
    @Min(value = 2000, message = "O ano de referência deve ser válido (a partir de 2000)")
    private int referenceYear;

    @NotBlank(message = "O status é obrigatório")
    // Dica: Se você tiver status fixos (ex: PAGO, PENDENTE), pode usar:
    @Pattern(regexp = "^(PAGO|PENDENTE|ATRASADO)$", message = "Status inválido")//regex para evitar que qualquer coisa diferente disso
    private String status;

    @NotNull(message = "O ID do membro é obrigatório")
    @Positive(message = "ID do membro inválido")
    private Long memberId;
}