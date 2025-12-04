package br.com.ifba.infrastructure.dto;

import lombok.Data;

@Data
public class UserTypeRequestDTO {
    private String typeName;
    private String description;
}