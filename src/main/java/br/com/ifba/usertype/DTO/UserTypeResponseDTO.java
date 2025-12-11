package br.com.ifba.usertype.DTO;

import lombok.Data;

@Data
public class UserTypeResponseDTO {
    private Long id;
    private String typeName;
    private String description;
}