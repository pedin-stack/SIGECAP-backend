package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.entity.Event;
import br.com.ifba.infrastructure.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events") // Rota base no plural
@CrossOrigin(origins = "http://localhost:3000") // CORS habilitado
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    //Listar Todos
    @GetMapping
    public ResponseEntity<List<Event>> findAll() {
        return ResponseEntity.ok(eventService.findAll());
    }

    //Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Event> findById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.findById(id));
    }

    // Criar
    @PostMapping
    public ResponseEntity<Event> save(@RequestBody Event event) {
        Event savedEvent = eventService.save(event);
        // Retorna Status 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
    }

    //Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Event> update(@PathVariable Long id, @RequestBody Event event) {
        // Garante consistência do ID da URL no objeto
        event.setId(id);
        Event updatedEvent = eventService.save(event);
        // Retorna Status 200 OK
        return ResponseEntity.ok(updatedEvent);
    }

    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventService.delete(id);
        // Retorna Status 204 No Content
        return ResponseEntity.noContent().build();
    }
}