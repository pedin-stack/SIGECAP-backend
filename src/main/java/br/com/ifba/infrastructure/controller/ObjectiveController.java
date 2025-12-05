package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.dto.ObjectiveRequestDTO;
import br.com.ifba.infrastructure.dto.ObjectiveResponseDTO;
import br.com.ifba.infrastructure.entity.Objective;
import br.com.ifba.infrastructure.entity.User;
import br.com.ifba.infrastructure.service.ObjectiveService;
import jakarta.validation.Valid; // <--- Importante
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/objectives")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class ObjectiveController {

    private final ObjectiveService objectiveService;
    private final ModelMapper modelMapper;

    private ObjectiveResponseDTO toDto(Objective entity) {
        return modelMapper.map(entity, ObjectiveResponseDTO.class);
    }

    private Objective toEntity(ObjectiveRequestDTO dto) {
        Objective entity = modelMapper.map(dto, Objective.class);

        if (dto.getCreatorId() != null) {
            User creatorStub = new User();
            creatorStub.setId(dto.getCreatorId());
            entity.setCreator(creatorStub);
        }

        return entity;
    }

    @GetMapping
    public ResponseEntity<List<ObjectiveResponseDTO>> findAll() {
        List<Objective> list = objectiveService.findAll();
        List<ObjectiveResponseDTO> dtos = list.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjectiveResponseDTO> findById(@PathVariable Long id) {
        Objective entity = objectiveService.findById(id);
        return ResponseEntity.ok(toDto(entity));
    }

    // Adicionado @Valid
    @PostMapping
    public ResponseEntity<ObjectiveResponseDTO> save(@RequestBody @Valid ObjectiveRequestDTO dto) {
        Objective entity = toEntity(dto);
        Objective saved = objectiveService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(saved));
    }

    // Adicionado @Valid
    @PutMapping("/{id}")
    public ResponseEntity<ObjectiveResponseDTO> update(@PathVariable Long id, @RequestBody @Valid ObjectiveRequestDTO dto) {
        Objective entity = toEntity(dto);
        entity.setId(id);
        Objective updated = objectiveService.save(entity);
        return ResponseEntity.ok(toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        objectiveService.delete(id);
        return ResponseEntity.noContent().build();
    }
}