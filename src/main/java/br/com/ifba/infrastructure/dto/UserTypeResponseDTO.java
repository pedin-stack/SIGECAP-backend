package br.com.ifba.infrastructure.dto;

import lombok.Data;

@Data
public class UserTypeResponseDTO {
    private Long id;
    private String typeName;
    private String description;
}