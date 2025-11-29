package br.com.ifba.infrastructure.entity.persistenceEntity;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;

import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;

import lombok.Getter;

import lombok.Setter;



/**

 *

 * @author User

 */



@MappedSuperclass // <--- ESSA ANOTAÇÃO É ESSENCIAL
@Getter
@Setter
public class PersistenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // ou IDENTITY
    private Long id;

}
