package br.com.ifba.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import br.com.ifba.infrastructure.entity.persistenceEntity.PersistenceEntity;

@Entity
@Table(name = "monthly_dues")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class MonthlyDues extends PersistenceEntity {

    private double value;
    private int referenceMonth;
    private int referenceYear;
    private String status;

    // Relação com User (Composição no diagrama)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User member;
}
