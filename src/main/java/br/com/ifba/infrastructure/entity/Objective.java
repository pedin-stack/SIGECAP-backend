package br.com.ifba.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.com.ifba.infrastructure.entity.persistenceEntity.PersistenceEntity;

@Entity
@Table(name = "objective")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Objective extends PersistenceEntity {

    private String description;
    private double financialGoal;
    private LocalDate deadline;

    // Relação com User (Associação no diagrama)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User creator;
}