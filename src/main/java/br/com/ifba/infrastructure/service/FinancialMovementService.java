package br.com.ifba.infrastructure.service;

import br.com.ifba.infrastructure.entity.FinancialMovement;
import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.infrastructure.repository.FinancialMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@RequiredArgsConstructor
@Service
public class FinancialMovementService {

    private final FinancialMovementRepository financialMovementRepository;

    public List<FinancialMovement> findAll() {
        return financialMovementRepository.findAll();
    }

    public FinancialMovement findById(Long id) {
        return financialMovementRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Movimentação financeira não encontrada com ID: " + id));
    }

    @Transactional
    public FinancialMovement save(FinancialMovement financialMovement) {
        return financialMovementRepository.save(financialMovement);
    }

    @Transactional
    public void delete(Long id) {
        if (!financialMovementRepository.existsById(id)) {
            throw new BusinessException("Movimentação financeira não encontrada para exclusão.");
        }
        financialMovementRepository.deleteById(id);
    }

    public List<FinancialMovement> findByDate(LocalDateTime date) {
        return financialMovementRepository.findByDate(date);
    }

    public List<FinancialMovement> findByValue(double value) {
        return financialMovementRepository.findByValue(value);
    }
}