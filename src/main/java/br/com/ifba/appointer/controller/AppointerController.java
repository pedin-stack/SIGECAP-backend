package br.com.ifba.appointer.controller;

import br.com.ifba.appointer.DTO.AppointerRequestDTO;
import br.com.ifba.appointer.DTO.AppointerResponseDTO;
import br.com.ifba.appointer.entity.Appointer;
import br.com.ifba.infrastructure.role.occupationRole;
import br.com.ifba.infrastructure.role.StatusRole;
import br.com.ifba.appointer.service.AppointerService;
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

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/appointers")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointerController {

    private final AppointerService appointerService;
    private final ModelMapper modelMapper;

    private AppointerResponseDTO toDto(Appointer appointer) {
        return modelMapper.map(appointer, AppointerResponseDTO.class);
    }

    private Appointer toEntity(AppointerRequestDTO dto) {
        return modelMapper.map(dto, Appointer.class);
    }

    @GetMapping
    public ResponseEntity<Page<AppointerResponseDTO>> findAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(appointerService.findAll(pageable).map(this::toDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointerResponseDTO> findById(@PathVariable Long id) {
        Appointer appointer = appointerService.findById(id);
        return ResponseEntity.ok(toDto(appointer));
    }

    // Adicionado @Valid (Valida inclusive a lista de membros interna)
    @PostMapping
    public ResponseEntity<AppointerResponseDTO> save(@RequestBody @Valid AppointerRequestDTO dto) {
        Appointer entity = toEntity(dto);
        Appointer saved = appointerService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(saved));
    }

    // Adicionado @Valid
    @PutMapping("/{id}")
    public ResponseEntity<AppointerResponseDTO> update(@PathVariable Long id, @RequestBody @Valid AppointerRequestDTO dto) {
        Appointer entity = toEntity(dto);
        entity.setId(id);
        Appointer updated = appointerService.save(entity);
        return ResponseEntity.ok(toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appointerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<AppointerResponseDTO> updateStatus(@PathVariable Long id, @RequestBody StatusRole newStatus) {
        // Enums simples no Body geralmente não precisam de @Valid, a menos que encapsulados em DTO
        Appointer updated = appointerService.updateStatus(id, newStatus);
        return ResponseEntity.ok(toDto(updated));
    }

    @GetMapping("/search/start-date")
    public ResponseEntity<List<AppointerResponseDTO>> findByStartDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Appointer> list = appointerService.findByStartDate(date);
        List<AppointerResponseDTO> dtos = list.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/search/end-date")
    public ResponseEntity<List<AppointerResponseDTO>> findByEndDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Appointer> list = appointerService.findByEndDate(date);
        List<AppointerResponseDTO> dtos = list.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/search/member-role")
    public ResponseEntity<List<AppointerResponseDTO>> findByMemberAndRole(
            @RequestParam("userId") Long userId,
            @RequestParam("role") occupationRole role) {
        List<Appointer> list = appointerService.findByMemberAndRole(userId, role);
        List<AppointerResponseDTO> dtos = list.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}