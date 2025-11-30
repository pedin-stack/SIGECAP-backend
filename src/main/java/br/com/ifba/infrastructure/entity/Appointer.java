package br.com.ifba.infrastructure.entity;

import br.com.ifba.infrastructure.entity.persistenceEntity.PersistenceEntity;
import jakarta.persistence.*;
import lombok.*;
import br.com.ifba.infrastructure.role.StatusRole;


import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "appointer") // Nominata
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Appointer extends PersistenceEntity {

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private double financialGoal;

    @Column(nullable = false)
    private StatusRole statusRole;

    // Em vez de 'private User scribe', 'private User treasurer'...
    // Temos uma lista de membros com seus cargos:
    @OneToMany(mappedBy = "appointer", cascade = CascadeType.ALL)
    private List<AppointerMember> members;
}