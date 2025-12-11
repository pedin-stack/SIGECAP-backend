package br.com.ifba.monthlydues.repository;

import br.com.ifba.monthlydues.entity.MonthlyDues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyDuesRepository extends JpaRepository<MonthlyDues, Long> {
    // CRUD básico fornecido pelo JpaRepository
}