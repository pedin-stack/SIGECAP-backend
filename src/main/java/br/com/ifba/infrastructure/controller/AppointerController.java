package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.dto.AppointerRequestDTO;
import br.com.ifba.infrastructure.dto.AppointerResponseDTO;
import br.com.ifba.infrastructure.entity.Appointer;
import br.com.ifba.infrastructure.role.DeMolayRole;
import br.com.ifba.infrastructure.role.StatusRole;
import br.com.ifba.infrastructure.service.AppointerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper; // Injeção do Mapper

    // =================================================================================
    // MÉTODOS AUXILIARES DE CONVERSÃO
    // =================================================================================

    private AppointerResponseDTO toDto(Appointer appointer) {
        return modelMapper.map(appointer, AppointerResponseDTO.class);
    }

    private Appointer toEntity(AppointerRequestDTO dto) {
        return modelMapper.map(dto, Appointer.class);
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<AppointerResponseDTO>> findAll() {
        List<Appointer> list = appointerService.findAll();
        List<AppointerResponseDTO> dtos = list.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<AppointerResponseDTO> findById(@PathVariable Long id) {
        Appointer appointer = appointerService.findById(id);
        return ResponseEntity.ok(toDto(appointer));
    }

    // Criar
    @PostMapping
    public ResponseEntity<AppointerResponseDTO> save(@RequestBody AppointerRequestDTO dto) {
        Appointer entity = toEntity(dto);
        Appointer saved = appointerService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(saved));
    }

    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<AppointerResponseDTO> update(@PathVariable Long id, @RequestBody AppointerRequestDTO dto) {
        Appointer entity = toEntity(dto);
        entity.setId(id);

        Appointer updated = appointerService.save(entity);
        return ResponseEntity.ok(toDto(updated));
    }

    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appointerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Atualizar Status
    @PutMapping("/{id}/status")
    public ResponseEntity<AppointerResponseDTO> updateStatus(@PathVariable Long id, @RequestBody StatusRole newStatus) {
        Appointer updated = appointerService.updateStatus(id, newStatus);
        return ResponseEntity.ok(toDto(updated));
    }

    // Buscar por Data de Início
    @GetMapping("/search/start-date")
    public ResponseEntity<List<AppointerResponseDTO>> findByStartDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Appointer> list = appointerService.findByStartDate(date);
        List<AppointerResponseDTO> dtos = list.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Buscar por Data de Término
    @GetMapping("/search/end-date")
    public ResponseEntity<List<AppointerResponseDTO>> findByEndDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Appointer> list = appointerService.findByEndDate(date);
        List<AppointerResponseDTO> dtos = list.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Buscar onde um membro teve um cargo específico
    @GetMapping("/search/member-role")
    public ResponseEntity<List<AppointerResponseDTO>> findByMemberAndRole(
            @RequestParam("userId") Long userId,
            @RequestParam("role") DeMolayRole role) {
        List<Appointer> list = appointerService.findByMemberAndRole(userId, role);
        List<AppointerResponseDTO> dtos = list.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}