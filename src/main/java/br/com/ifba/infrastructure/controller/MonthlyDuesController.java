package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.dto.MonthlyDuesRequestDTO;
import br.com.ifba.infrastructure.dto.MonthlyDuesResponseDTO;
import br.com.ifba.infrastructure.entity.MonthlyDues;
import br.com.ifba.infrastructure.entity.User;
import br.com.ifba.infrastructure.service.MonthlyDuesService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/monthly-dues")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class MonthlyDuesController {

    private final MonthlyDuesService monthlyDuesService;
    private final ModelMapper modelMapper;

    // =================================================================================
    // CONVERSORES
    // =================================================================================

    private MonthlyDuesResponseDTO toDto(MonthlyDues entity) {
        // O ModelMapper mapeia member.name -> memberName automaticamente
        return modelMapper.map(entity, MonthlyDuesResponseDTO.class);
    }

    private MonthlyDues toEntity(MonthlyDuesRequestDTO dto) {
        MonthlyDues entity = modelMapper.map(dto, MonthlyDues.class);

        // Mapeamento manual do relacionamento com User (Membro)
        if (dto.getMemberId() != null) {
            User memberStub = new User();
            memberStub.setId(dto.getMemberId());
            entity.setMember(memberStub);
        }

        return entity;
    }

    // =================================================================================
    // CRUD
    // =================================================================================

    // Listar Todos
    @GetMapping
    public ResponseEntity<List<MonthlyDuesResponseDTO>> findAll() {
        List<MonthlyDues> list = monthlyDuesService.findAll();
        List<MonthlyDuesResponseDTO> dtos = list.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<MonthlyDuesResponseDTO> findById(@PathVariable Long id) {
        MonthlyDues entity = monthlyDuesService.findById(id);
        return ResponseEntity.ok(toDto(entity));
    }

    // Criar
    @PostMapping
    public ResponseEntity<MonthlyDuesResponseDTO> save(@RequestBody MonthlyDuesRequestDTO dto) {
        MonthlyDues entity = toEntity(dto);
        MonthlyDues saved = monthlyDuesService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(saved));
    }

    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<MonthlyDuesResponseDTO> update(@PathVariable Long id, @RequestBody MonthlyDuesRequestDTO dto) {
        MonthlyDues entity = toEntity(dto);
        entity.setId(id); // Garante consistência do ID

        MonthlyDues updated = monthlyDuesService.save(entity);
        return ResponseEntity.ok(toDto(updated));
    }

    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        monthlyDuesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}