package br.com.ifba.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import br.com.ifba.infrastructure.entity.Attendance;
import br.com.ifba.infrastructure.entity.Event
        ;
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // Listar quem estava presente em um evento específico (RF06 - Escrivão)
    // O Spring entende 'findByEventId' navegando na propriedade 'event' e pegando o 'id'
    List<Attendance> findByEventId(Long eventId);

    // Histórico de presença de um membro específico (RF10 - Relatório)
    List<Attendance> findByMemberId(Long memberId);

    // Evitar que o mesmo membro tenha 2 registros no mesmo evento
    boolean existsByMemberIdAndEventId(Long memberId, Long eventId);

    // Contar quantas faltas um membro tem (para estatísticas)
    long countByMemberIdAndStatus(Event event, boolean status);
}