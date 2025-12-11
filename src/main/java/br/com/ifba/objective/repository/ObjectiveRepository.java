package br.com.ifba.objective.repository;

import br.com.ifba.objective.entity.Objective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectiveRepository extends JpaRepository<Objective, Long> {
    // CRUD básico fornecido pelo JpaRepository
}