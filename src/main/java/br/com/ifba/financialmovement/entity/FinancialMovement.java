package br.com.ifba.financialmovement.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import br.com.ifba.infrastructure.entity.persistenceEntity.PersistenceEntity;
import br.com.ifba.user.entity.User;

@Entity
@Table(name = "financial_movement")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class FinancialMovement extends PersistenceEntity {
    @Column(nullable = false)
    private double value;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private String type; // Entrada ou Saida

    @Column(nullable = false)
    private String supportingDoc; // URL ou caminho do arquivo

    // Relação com User (Associação no diagrama)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User responsible;
}