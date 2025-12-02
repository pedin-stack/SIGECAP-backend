package br.com.ifba.infrastructure.repository;

import br.com.ifba.infrastructure.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    // O JpaRepository já fornece todos os métodos de CRUD necessários
}