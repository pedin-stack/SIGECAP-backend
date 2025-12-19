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
@Table(name = "users")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class User extends Person {

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "user_type_id")
    private UserType userType;

    @OneToMany(mappedBy = "member")
    private List<MonthlyDues> monthlyDues;

    @OneToMany(mappedBy = "responsible")
    private List<FinancialMovement> financialMovements;

    @OneToMany(mappedBy = "creator")
    private List<Objective> objectives;
}
