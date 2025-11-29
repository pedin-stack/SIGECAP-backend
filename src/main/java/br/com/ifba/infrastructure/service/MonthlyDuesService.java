package br.com.ifba.infrastructure.service;

import br.com.ifba.infrastructure.entity.MonthlyDues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.ifba.infrastructure.repository.MonthlyDuesRepository;
@Service
public class MonthlyDuesService {

    @Autowired
    private MonthlyDuesRepository duesRepository;

    public List<MonthlyDues> findByUser(Long userId) {
        return duesRepository.findByMemberId(userId);
    }

    public MonthlyDues save(MonthlyDues due) {
        // Verificar se já existe mensalidade para aquele mês/ano e usuário
        boolean exists = duesRepository.existsByMemberIdAndReferenceMonthAndReferenceYear(
                due.getMember().getId(), due.getReferenceMonth(), due.getReferenceYear());

        if (exists) {
            throw new RuntimeException("Mensalidade já gerada para este período.");
        }

     //   due.setStatus(DuesStatus.PENDENTE);
        return duesRepository.save(due);
    }

    public MonthlyDues registerPayment(Long id) {
        MonthlyDues due = duesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mensalidade não encontrada"));

      //  due.setStatus(DuesStatus.PAGO);
        // due.setPaymentDate(LocalDateTime.now());

        return duesRepository.save(due);
    }
}