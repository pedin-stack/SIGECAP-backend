package br.com.ifba.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import br.com.ifba.infrastructure.entity.persistenceEntity.PersistenceEntity;
import br.com.ifba.infrastructure.entity.User;

@Entity
@Table(name = "financial_movement")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class FinancialMovement extends PersistenceEntity {

    private double value;
    private String description;
    private LocalDateTime date;
    private String type; // Entrada ou Saida
    private String supportingDoc; // URL ou caminho do arquivo

    // Relação com User (Associação no diagrama)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User responsible;
}