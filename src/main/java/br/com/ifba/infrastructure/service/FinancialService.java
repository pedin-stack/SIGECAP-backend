package br.com.ifba.infrastructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import br.com.ifba.infrastructure.entity.FinancialMovement;
import br.com.ifba.infrastructure.repository.FinancialMovementRepository;



@Service
public class FinancialService {

    @Autowired
    private FinancialMovementRepository financialRepository;

    public List<FinancialMovement> findAll() {
        return financialRepository.findAll();
    }

    public FinancialMovement save(FinancialMovement movement) {
        //Toda movimentação nasce como PENDENTE de aprovação
        //movement.setStatus(FinancialStatus.PENDENTE);
       // movement.setDate(LocalDateTime.now());

        //Validar valores negativos
        if (movement.getValue() <= 0) {
            throw new RuntimeException("O valor deve ser positivo.");
        }

        return financialRepository.save(movement);
    }

    public FinancialMovement approve(Long id) {
        FinancialMovement movement = financialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimentação não encontrada"));

        return financialRepository.save(movement);
    }
}
