package br.com.ifba.event.repository;

import br.com.ifba.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    // O JpaRepository já fornece todos os métodos de CRUD necessários
}