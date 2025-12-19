package br.com.ifba.person.entity;

import jakarta.persistence.*; // Removeu Inheritence
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import br.com.ifba.infrastructure.entity.persistenceEntity.PersistenceEntity;

import java.time.LocalDate;

@MappedSuperclass // <--- A grande mudança é aqui
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor

public abstract class Person extends PersistenceEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true) // CPF geralmente é único
    private String cpf;

    @Column(name = "birth_date", nullable = false) // Boa prática: snake_case no banco
    private LocalDate birthDate;

    @Column(nullable = false)
    private String phone;
}