package br.com.ifba.objective.entity;

import br.com.ifba.user.entity.User;
import jakarta.persistence.*;

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

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double financialGoal;

    @Column(nullable = false)
    private LocalDate deadline;

    // Relação com User (Associação no diagrama)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User creator;
}