package br.com.ifba.infrastructure.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import br.com.ifba.infrastructure.entity.persistenceEntity.PersistenceEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.JoinTable;


@Entity
@Table(name = "event")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Event extends PersistenceEntity {

    private String name;
    private LocalDateTime date;
    private String local;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean status;//provisorio

    // No diagrama aparece eventPics. Assumindo String para URL ou List<String>
    private String eventPics;

    // Lista de presença (Agregação com User no diagrama)
    @ManyToMany
    @JoinTable(
            name = "event_attendance",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> presenceList;
}
