package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.dto.FinancialMovementRequestDTO;
import br.com.ifba.infrastructure.dto.FinancialMovementResponseDTO;
import br.com.ifba.infrastructure.entity.FinancialMovement;
import br.com.ifba.infrastructure.entity.User;
import br.com.ifba.infrastructure.service.FinancialMovementService;
import jakarta.validation.Valid; // <--- Importante
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/financial-movements")
@CrossOrigin(origins = "http://localhost:3000")
public class FinancialMovementController {

    private final FinancialMovementService financialMovementService;
    private final ModelMapper modelMapper;

    private FinancialMovementResponseDTO toDto(FinancialMovement entity) {
        return modelMapper.map(entity, FinancialMovementResponseDTO.class);
    }

    private FinancialMovement toEntity(FinancialMovementRequestDTO dto) {
        FinancialMovement entity = modelMapper.map(dto, FinancialMovement.class);

        if (dto.getResponsibleId() != null) {
            User userStub = new User();
            userStub.setId(dto.getResponsibleId());
            entity.setResponsible(userStub);
        }

        return entity;
    }

    @GetMapping
    public ResponseEntity<Page<FinancialMovementResponseDTO>> findAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(financialMovementService.findAll(pageable).map(this::toDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinancialMovementResponseDTO> findById(@PathVariable Long id) {
        FinancialMovement entity = financialMovementService.findById(id);
        return ResponseEntity.ok(toDto(entity));
    }

    // Adicionado @Valid
    @PostMapping
    public ResponseEntity<FinancialMovementResponseDTO> save(@RequestBody @Valid FinancialMovementRequestDTO dto) {
        FinancialMovement entity = toEntity(dto);
        FinancialMovement saved = financialMovementService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(saved));
    }

    // Adicionado @Valid
    @PutMapping("/{id}")
    public ResponseEntity<FinancialMovementResponseDTO> update(@PathVariable Long id, @RequestBody @Valid FinancialMovementRequestDTO dto) {
        FinancialMovement entity = toEntity(dto);
        entity.setId(id);
        FinancialMovement updated = financialMovementService.save(entity);
        return ResponseEntity.ok(toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        financialMovementService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/date")
    public ResponseEntity<List<FinancialMovementResponseDTO>> findByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        List<FinancialMovement> list = financialMovementService.findByDate(date);
        List<FinancialMovementResponseDTO> dtos = list.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/search/value")
    public ResponseEntity<List<FinancialMovementResponseDTO>> findByValue(@RequestParam("value") double value) {
        List<FinancialMovement> list = financialMovementService.findByValue(value);
        List<FinancialMovementResponseDTO> dtos = list.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}