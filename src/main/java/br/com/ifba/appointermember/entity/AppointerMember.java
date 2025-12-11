package br.com.ifba.appointermember.entity;

import br.com.ifba.appointer.entity.Appointer;
import br.com.ifba.user.entity.User;
import br.com.ifba.infrastructure.entity.persistenceEntity.PersistenceEntity;
import br.com.ifba.infrastructure.role.DeMolayRole;
import jakarta.persistence.*;
import lombok.*;
// ENTIDADE DE RELACIONAMENTO
@Entity
@Table(name = "appointer_member")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class AppointerMember extends PersistenceEntity {

    @ManyToOne
    @JoinColumn(name = "appointer_id")
    private Appointer appointer; // A qual gestão pertence

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Quem é o DeMolay

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeMolayRole role; // Qual o cargo
}