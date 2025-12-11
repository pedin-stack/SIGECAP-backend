package br.com.ifba.objective.service;

import br.com.ifba.objective.entity.Objective;
import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.objective.repository.ObjectiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ObjectiveService {

    private final ObjectiveRepository objectiveRepository;

    // Listar todos
    public Page<Objective> findAll(Pageable pageable) {
        return objectiveRepository.findAll(pageable);
    }

    // Buscar por ID
    public Objective findById(Long id) {
        return objectiveRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Objetivo não encontrado com ID: " + id));
    }

    // Salvar (Criar ou Atualizar)
    @Transactional
    public Objective save(Objective objective) {
        return objectiveRepository.save(objective);
    }

    // Deletar
    @Transactional
    public void delete(Long id) {
        if (!objectiveRepository.existsById(id)) {
            throw new BusinessException("Objetivo não encontrado para exclusão.");
        }
        objectiveRepository.deleteById(id);
    }
}