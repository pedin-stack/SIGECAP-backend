package br.com.ifba.financialmovement.repository;

import br.com.ifba.financialmovement.entity.FinancialMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FinancialMovementRepository extends JpaRepository<FinancialMovement, Long> {

    List<FinancialMovement> findByDate(LocalDateTime date);

    List<FinancialMovement> findByValue(double value);
}