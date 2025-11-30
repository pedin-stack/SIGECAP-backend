package br.com.ifba.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_attendance")
@Data
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private User member;

    //@Enumerated(EnumType.STRING)
    private boolean status; // depois mudar para o enum PRESENTE, AUSENTE, JUSTIFICADO

    @Column(length = 500)
    private String justification; // Texto opcional
}