package br.com.ifba.infrastructure.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import br.com.ifba.infrastructure.entity.Event; // Certifique-se de importar sua Entity
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

    private final List<Event> events = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(events);
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        event.setId(idCounter.getAndIncrement());
        // Define status inicial padrão para teste
        // event.setStatus(EventStatus.AGENDADO);
        events.add(event);
        return ResponseEntity.status(201).body(event);
    }

    @PutMapping("/{id}/confirm")
    public ResponseEntity<Event> confirmEventRealization(@PathVariable Long id) {
        Optional<Event> eventOpt = events.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();

        if (eventOpt.isPresent()) {
            Event event = eventOpt.get();
            // Simula a mudança de status
            // event.setStatus(EventStatus.REALIZADO);
            return ResponseEntity.ok(event);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        events.removeIf(e -> e.getId().equals(id));
        return ResponseEntity.noContent().build();
    }
}