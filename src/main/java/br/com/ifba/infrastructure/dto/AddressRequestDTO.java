package br.com.ifba.infrastructure.dto;

import lombok.Data;

@Data
public class AddressRequestDTO {
    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private String state;
    private String cep;
}