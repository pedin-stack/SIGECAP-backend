package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.dto.MonthlyDuesRequestDTO;
import br.com.ifba.infrastructure.dto.MonthlyDuesResponseDTO;
import br.com.ifba.infrastructure.entity.MonthlyDues;
import br.com.ifba.infrastructure.entity.User;
import br.com.ifba.infrastructure.service.MonthlyDuesService;
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
@RequestMapping("/api/monthly-dues")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class MonthlyDuesController {

    private final MonthlyDuesService monthlyDuesService;
    private final ModelMapper modelMapper;

    private MonthlyDuesResponseDTO toDto(MonthlyDues entity) {
        return modelMapper.map(entity, MonthlyDuesResponseDTO.class);
    }

    private MonthlyDues toEntity(MonthlyDuesRequestDTO dto) {
        MonthlyDues entity = modelMapper.map(dto, MonthlyDues.class);

        if (dto.getMemberId() != null) {
            User memberStub = new User();
            memberStub.setId(dto.getMemberId());
            entity.setMember(memberStub);
        }

        return entity;
    }

    @GetMapping
    public ResponseEntity<Page<MonthlyDuesResponseDTO>> findAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(monthlyDuesService.findAll(pageable).map(this::toDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonthlyDuesResponseDTO> findById(@PathVariable Long id) {
        MonthlyDues entity = monthlyDuesService.findById(id);
        return ResponseEntity.ok(toDto(entity));
    }

    // Adicionado @Valid
    @PostMapping
    public ResponseEntity<MonthlyDuesResponseDTO> save(@RequestBody @Valid MonthlyDuesRequestDTO dto) {
        MonthlyDues entity = toEntity(dto);
        MonthlyDues saved = monthlyDuesService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(saved));
    }

    // Adicionado @Valid
    @PutMapping("/{id}")
    public ResponseEntity<MonthlyDuesResponseDTO> update(@PathVariable Long id, @RequestBody @Valid MonthlyDuesRequestDTO dto) {
        MonthlyDues entity = toEntity(dto);
        entity.setId(id);
        MonthlyDues updated = monthlyDuesService.save(entity);
        return ResponseEntity.ok(toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        monthlyDuesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}