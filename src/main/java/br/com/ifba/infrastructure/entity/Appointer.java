package br.com.ifba.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_appointer")
@Data
public class Appointer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String managementName; // Ex: "Gestão 2025.1 - Fênix"

    private LocalDate startDate;
    private LocalDate endDate;
// enum com os cargos
    //@Enumerated(EnumType.STRING) depois mudar para enum
    private boolean status; // PROPOSTA, ATIVA, HISTORICO

    // Uma gestão tem vários membros ocupando cargos
    @ManyToMany
    @JoinTable(
            name = "tb_appointer_members",
            joinColumns = @JoinColumn(name = "appointer_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> managementMembers;
}