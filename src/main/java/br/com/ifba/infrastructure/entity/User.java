package br.com.ifba.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Entity
@Table(name = "users") // "user" é palavra reservada em alguns bancos
@PrimaryKeyJoinColumn(name = "person_id")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class User extends Person {

    private String email;
    private String password;
    private boolean active;

    // Associações diretas do diagrama
    @ManyToOne
    @JoinColumn(name = "user_type_id")
    private UserType userType;

    // Listas indicadas pelas setas/linhas do diagrama partindo de User ou para User
    @OneToMany(mappedBy = "member")
    private List<MonthlyDues> monthlyDues;

    @OneToMany(mappedBy = "responsible") // Considerando que o usuário é responsável pela movimentação
    private List<FinancialMovement> financialMovements;

    @OneToMany(mappedBy = "creator") // Considerando que o usuário cria o objetivo
    private List<Objective> objectives;
}
