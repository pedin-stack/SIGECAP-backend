package br.com.ifba.infrastructure.service;

import br.com.ifba.infrastructure.entity.Objective;
import br.com.ifba.infrastructure.repository.ObjectiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ObjectiveService {

    private final ObjectiveRepository objectiveRepository;

    // Listar todos
    public List<Objective> findAll() {
        return objectiveRepository.findAll();
    }

    // Buscar por ID
    public Objective findById(Long id) {
        return objectiveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Objetivo não encontrado com ID: " + id));
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
            throw new RuntimeException("Objetivo não encontrado para exclusão.");
        }
        objectiveRepository.deleteById(id);
    }
}