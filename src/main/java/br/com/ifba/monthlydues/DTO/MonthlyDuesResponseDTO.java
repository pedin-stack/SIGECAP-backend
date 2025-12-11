package br.com.ifba.monthlydues.DTO;

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