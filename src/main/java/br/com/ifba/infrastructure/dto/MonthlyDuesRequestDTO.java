package br.com.ifba.infrastructure.dto;

import lombok.Data;

@Data
public class MonthlyDuesRequestDTO {
    private double value;
    private int referenceMonth;
    private int referenceYear;
    private String status;
    private Long memberId; // ID do Membro (User)
}