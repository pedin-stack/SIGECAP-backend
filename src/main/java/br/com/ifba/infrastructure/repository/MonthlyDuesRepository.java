package br.com.ifba.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import br.com.ifba.infrastructure.entity.MonthlyDues;
@Repository
public interface MonthlyDuesRepository extends JpaRepository<MonthlyDues, Long> {

    // Ver todas as mensalidades de um membro (Histórico pessoal - RF08)
    List<MonthlyDues> findByMemberId(Long memberId);

    // Metodo para impedir duplicidade de cobrança no mesmo mês
    boolean existsByMemberIdAndReferenceMonthAndReferenceYear(Long memberId, int referenceMonth, int referenceYear);

}
