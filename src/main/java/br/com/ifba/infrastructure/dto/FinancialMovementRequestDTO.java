package br.com.ifba.infrastructure.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FinancialMovementRequestDTO {

    // Usamos @Positive para impedir valores negativos ou zero.
    // Como existe o campo "type" (Saída), o valor numérico deve ser absoluto (ex: 50.00).
    @Positive(message = "O valor da movimentação deve ser maior que zero")
    private double value;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(min = 5, max = 255, message = "A descrição deve ter entre 5 e 255 caracteres")
    private String description;

    @NotNull(message = "A data da movimentação é obrigatória")
    // Opcional: @PastOrPresent se quiser impedir lançamentos futuros
    private LocalDateTime date;

    @NotBlank(message = "O tipo de movimentação é obrigatório")
    // Regex: Obriga que o texto seja exatamente "ENTRADA" ou "SAIDA"
    @Pattern(regexp = "^(ENTRADA|SAIDA)$", message = "O tipo deve ser 'ENTRADA' ou 'SAIDA'")
    private String type;

    // Assumindo que o comprovante é obrigatório para transparência financeira
    @NotBlank(message = "O link/caminho do comprovante é obrigatório")
    @Size(max = 500, message = "O caminho do documento é muito longo")
    private String supportingDoc;

    @NotNull(message = "O ID do responsável é obrigatório")
    @Positive(message = "ID do responsável inválido")
    private Long responsibleId;
}