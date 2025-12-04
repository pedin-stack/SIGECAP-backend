package br.com.ifba.infrastructure.dto;

import lombok.Data;

@Data
public class MonthlyDuesResponseDTO {
    private Long id;
    private double value;
    private int referenceMonth;
    private int referenceYear;
    private String status;

    // Dados achatados do Membro
    private Long memberId;
    private String memberName;
}