package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.dto.EventRequestDTO;
import br.com.ifba.infrastructure.dto.EventResponseDTO;
import br.com.ifba.infrastructure.entity.Event;
import br.com.ifba.infrastructure.service.EventService;
import jakarta.validation.Valid; // <--- Importante
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    private EventResponseDTO toDto(Event event) {
        return modelMapper.map(event, EventResponseDTO.class);
    }

    private Event toEntity(EventRequestDTO dto) {
        return modelMapper.map(dto, Event.class);
    }

    @GetMapping
    public ResponseEntity<Page<EventResponseDTO>> findAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(eventService.findAll(pageable).map(this::toDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> findById(@PathVariable Long id) {
        Event event = eventService.findById(id);
        return ResponseEntity.ok(toDto(event));
    }

    // Adicionado @Valid
    @PostMapping
    public ResponseEntity<EventResponseDTO> save(@RequestBody @Valid EventRequestDTO dto) {
        Event entity = toEntity(dto);
        Event savedEvent = eventService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(savedEvent));
    }

    // Adicionado @Valid
    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDTO> update(@PathVariable Long id, @RequestBody @Valid EventRequestDTO dto) {
        Event entity = toEntity(dto);
        entity.setId(id);
        Event updatedEvent = eventService.save(entity);
        return ResponseEntity.ok(toDto(updatedEvent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }
}