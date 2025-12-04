package br.com.ifba.infrastructure.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FinancialMovementRequestDTO {
    private double value;
    private String description;
    private LocalDateTime date;
    private String type; // "ENTRADA" ou "SAIDA"
    private String supportingDoc; // URL do comprovante
    private Long responsibleId; // ID do Tesoureiro/Responsável
}