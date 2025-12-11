package br.com.ifba.user.entity;

import br.com.ifba.financialmovement.entity.FinancialMovement;
import br.com.ifba.usertype.entity.UserType;
import br.com.ifba.monthlydues.entity.MonthlyDues;
import br.com.ifba.objective.entity.Objective;
import br.com.ifba.person.entity.Person;
import jakarta.persistence.*;

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

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean isactive;

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
