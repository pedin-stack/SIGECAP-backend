package br.com.ifba.person.DTO;

import br.com.ifba.infrastructure.dto.AddressResponseDTO;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PersonResponseDTO {
    private Long id;
    private String name;
    private String cpf;
    private LocalDate birthDate;
    private String contact;

    // Aninhando o DTO de endereço para exibir os detalhes (Rua, Cidade, etc)
    private AddressResponseDTO address;
}