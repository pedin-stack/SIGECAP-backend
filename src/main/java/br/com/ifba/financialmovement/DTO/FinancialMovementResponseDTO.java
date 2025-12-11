package br.com.ifba.financialmovement.DTO;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FinancialMovementResponseDTO {
    private Long id;
    private double value;
    private String description;
    private LocalDateTime date;
    private String type;
    private String supportingDoc;

    // Dados achatados do Responsável
    private Long responsibleId;
    private String responsibleName;
}