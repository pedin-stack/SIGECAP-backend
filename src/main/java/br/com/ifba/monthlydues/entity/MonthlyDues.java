package br.com.ifba.monthlydues.entity;

import br.com.ifba.user.entity.User;
import jakarta.persistence.*;
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

    @Column(nullable = false)
    private double value;

    @Column(nullable = false)
    private int referenceMonth;

    @Column(nullable = false)
    private int referenceYear;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User member;
}
