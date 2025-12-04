package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.dto.ObjectiveRequestDTO;
import br.com.ifba.infrastructure.dto.ObjectiveResponseDTO;
import br.com.ifba.infrastructure.entity.Objective;
import br.com.ifba.infrastructure.entity.User;
import br.com.ifba.infrastructure.service.ObjectiveService;
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

    // =================================================================================
    // CONVERSORES
    // =================================================================================

    private ObjectiveResponseDTO toDto(Objective entity) {
        // O ModelMapper mapeia creator.name -> creatorName automaticamente
        return modelMapper.map(entity, ObjectiveResponseDTO.class);
    }

    private Objective toEntity(ObjectiveRequestDTO dto) {
        Objective entity = modelMapper.map(dto, Objective.class);

        // Mapeamento manual do relacionamento com User (Criador)
        if (dto.getCreatorId() != null) {
            User creatorStub = new User();
            creatorStub.setId(dto.getCreatorId());
            entity.setCreator(creatorStub);
        }

        return entity;
    }

    // =================================================================================
    // CRUD
    // =================================================================================

    // Listar Todos
    @GetMapping
    public ResponseEntity<List<ObjectiveResponseDTO>> findAll() {
        List<Objective> list = objectiveService.findAll();
        List<ObjectiveResponseDTO> dtos = list.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<ObjectiveResponseDTO> findById(@PathVariable Long id) {
        Objective entity = objectiveService.findById(id);
        return ResponseEntity.ok(toDto(entity));
    }

    // Criar
    @PostMapping
    public ResponseEntity<ObjectiveResponseDTO> save(@RequestBody ObjectiveRequestDTO dto) {
        Objective entity = toEntity(dto);
        Objective saved = objectiveService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(saved));
    }

    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<ObjectiveResponseDTO> update(@PathVariable Long id, @RequestBody ObjectiveRequestDTO dto) {
        Objective entity = toEntity(dto);
        entity.setId(id); // Garante consistência

        Objective updated = objectiveService.save(entity);
        return ResponseEntity.ok(toDto(updated));
    }

    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        objectiveService.delete(id);
        return ResponseEntity.noContent().build();
    }
}