package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.dto.EventRequestDTO;
import br.com.ifba.infrastructure.dto.EventResponseDTO;
import br.com.ifba.infrastructure.entity.Event;
import br.com.ifba.infrastructure.service.EventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final ModelMapper modelMapper;

    // =================================================================================
    // CONVERSORES
    // =================================================================================

    private EventResponseDTO toDto(Event event) {
        return modelMapper.map(event, EventResponseDTO.class);
    }

    private Event toEntity(EventRequestDTO dto) {
        return modelMapper.map(dto, Event.class);
    }

    // =================================================================================
    // CRUD
    // =================================================================================

    // Listar Todos
    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> findAll() {
        List<Event> events = eventService.findAll();

        List<EventResponseDTO> dtos = events.stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> findById(@PathVariable Long id) {
        Event event = eventService.findById(id);
        return ResponseEntity.ok(toDto(event));
    }

    // Criar
    @PostMapping
    public ResponseEntity<EventResponseDTO> save(@RequestBody EventRequestDTO dto) {
        Event entity = toEntity(dto);
        Event savedEvent = eventService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(savedEvent));
    }

    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDTO> update(@PathVariable Long id, @RequestBody EventRequestDTO dto) {
        Event entity = toEntity(dto);
        entity.setId(id); // Garante a consistência do ID

        Event updatedEvent = eventService.save(entity);
        return ResponseEntity.ok(toDto(updatedEvent));
    }

    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }
}