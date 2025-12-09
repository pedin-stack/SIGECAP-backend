package br.com.ifba.infrastructure.service;

import br.com.ifba.infrastructure.entity.Event;
import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.infrastructure.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;

    // Listar todos
    public Page<Event> findAll(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    // Buscar por ID
    public Event findById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Evento não encontrado com ID: " + id));
    }

    // Salvar
    @Transactional
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    // Deletar
    @Transactional
    public void delete(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new BusinessException("Evento não encontrado para exclusão.");
        }
        eventRepository.deleteById(id);
    }
}