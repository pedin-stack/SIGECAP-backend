package br.com.ifba.monthlydues.service;

import br.com.ifba.monthlydues.entity.MonthlyDues;
import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.monthlydues.repository.MonthlyDuesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MonthlyDuesService {

    private final MonthlyDuesRepository monthlyDuesRepository;

    // Listar todos
    public Page<MonthlyDues> findAll(Pageable pageable) {
        return  monthlyDuesRepository.findAll(pageable);
    }

    // Buscar por ID
    public MonthlyDues findById(Long id) {
        return monthlyDuesRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Mensalidade não encontrada com ID: " + id));
    }

    // Salvar (Criar ou Atualizar)
    @Transactional
    public MonthlyDues save(MonthlyDues monthlyDues) {
        return monthlyDuesRepository.save(monthlyDues);
    }

    // Deletar
    @Transactional
    public void delete(Long id) {
        if (!monthlyDuesRepository.existsById(id)) {
            throw new BusinessException("Mensalidade não encontrada para exclusão.");
        }
        monthlyDuesRepository.deleteById(id);
    }
}