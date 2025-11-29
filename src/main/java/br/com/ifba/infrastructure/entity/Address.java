package br.com.ifba.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import br.com.ifba.infrastructure.entity.persistenceEntity.PersistenceEntity;

@Entity
@Table(name = "address")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Address extends PersistenceEntity {

    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private String state;
    private String cep;
}