package br.com.ifba.event.entity;


import br.com.ifba.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import br.com.ifba.infrastructure.entity.persistenceEntity.PersistenceEntity;

import java.time.LocalDateTime;
import java.util.List;

import br.com.ifba.infrastructure.role.StatusRole;

@Entity
@Table(name = "event")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Event extends PersistenceEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private String local;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private StatusRole statusRole;

    @Column(nullable = false)
    private String eventPics;

    @ManyToMany
    @JoinTable(
            name = "event_attendance",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> presenceList;
}
