package br.com.ifba.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.ifba.infrastructure.entity.Event;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);

    // Buscar próximos eventos (a partir de hoje) ordenados por data
    List<Event> findByStartTimeAfterOrderByStartTimeAsc(LocalDateTime date);
}