package br.com.ifba.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import br.com.ifba.infrastructure.entity.FinancialMovement;

@Repository
public interface FinancialMovementRepository extends JpaRepository<FinancialMovement, Long> {

    List<FinancialMovement> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);


}