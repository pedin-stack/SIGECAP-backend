package br.com.ifba.infrastructure.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    private String typeName;

    @Column(nullable = false)
    private String description;
}
