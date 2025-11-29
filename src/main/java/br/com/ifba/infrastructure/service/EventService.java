package br.com.ifba.infrastructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import br.com.ifba.infrastructure.repository.EventRepository;
import br.com.ifba.infrastructure.entity.Event;
import java.util.List;
@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event save(Event event) {

        if (event.getStartTime().isAfter(event.getEndTime())) {
            throw new RuntimeException("A data de início não pode ser posterior ao fim.");
        }
        return eventRepository.save(event);
    }

    public Event confirmRealization(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        //Só pode confirmar se a data já passou ou é hoje
        if (event.getStartTime().isAfter(LocalDateTime.now())) {
            throw new RuntimeException("Não é possível confirmar um evento futuro.");
        }

        return eventRepository.save(event);
    }

    public void delete(Long id) {
        eventRepository.deleteById(id);
    }
}