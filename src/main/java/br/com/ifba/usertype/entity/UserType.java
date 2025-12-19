package br.com.ifba.usertype.entity;
import br.com.ifba.infrastructure.role.TypeRole;
import br.com.ifba.infrastructure.role.occupationRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import br.com.ifba.infrastructure.entity.persistenceEntity.PersistenceEntity;

@Entity
@Table(name = "user_type")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UserType extends PersistenceEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeRole description ;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private occupationRole  occupation;
}
