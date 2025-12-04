package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.dto.AttendanceRequestDTO;
import br.com.ifba.infrastructure.dto.AttendanceResponseDTO;
import br.com.ifba.infrastructure.entity.Attendance;
import br.com.ifba.infrastructure.entity.Event;
import br.com.ifba.infrastructure.entity.User;
import br.com.ifba.infrastructure.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/attendances")
@CrossOrigin(origins = "http://localhost:3000")
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final ModelMapper modelMapper;

    // =================================================================================
    // CONVERSORES (DTO <-> Entity)
    // =================================================================================

    private AttendanceResponseDTO toDto(Attendance entity) {
        // O ModelMapper tentará mapear automaticamente:
        // event.name -> eventName
        // member.name ≥ memberName
        // Se houver discrepância, ele deixará null, mas geralmente funciona bem para nomes padrão.
        return modelMapper.map(entity, AttendanceResponseDTO.class);
    }

    private Attendance toEntity(AttendanceRequestDTO dto) {
        Attendance entity = modelMapper.map(dto, Attendance.class);

        // Configuração manual dos relacionamentos pelos IDs (Safe Mapping)
        // Isso impede que o ModelMapper se perca tentando converter Long para Objeto

        if (dto.getEventId() != null) {
            Event eventStub = new Event();
            eventStub.setId(dto.getEventId());
            entity.setEvent(eventStub);
        }

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
    public ResponseEntity<List<AttendanceResponseDTO>> findAll() {
        List<Attendance> list = attendanceService.findAll();
        List<AttendanceResponseDTO> dtos = list.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<AttendanceResponseDTO> findById(@PathVariable Long id) {
        Attendance entity = attendanceService.findById(id);
        return ResponseEntity.ok(toDto(entity));
    }

    // Criar
    @PostMapping
    public ResponseEntity<AttendanceResponseDTO> save(@RequestBody AttendanceRequestDTO dto) {
        Attendance entity = toEntity(dto);
        Attendance saved = attendanceService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(saved));
    }

    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<AttendanceResponseDTO> update(@PathVariable Long id, @RequestBody AttendanceRequestDTO dto) {
        Attendance entity = toEntity(dto);
        entity.setId(id); // Garante o ID da URL

        Attendance updated = attendanceService.save(entity);
        return ResponseEntity.ok(toDto(updated));
    }

    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        attendanceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}