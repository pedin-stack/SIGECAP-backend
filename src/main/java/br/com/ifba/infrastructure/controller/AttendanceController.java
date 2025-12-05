package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.dto.AttendanceRequestDTO;
import br.com.ifba.infrastructure.dto.AttendanceResponseDTO;
import br.com.ifba.infrastructure.entity.Attendance;
import br.com.ifba.infrastructure.entity.Event;
import br.com.ifba.infrastructure.entity.User;
import br.com.ifba.infrastructure.service.AttendanceService;
import jakarta.validation.Valid; // <--- Importante
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

    private AttendanceResponseDTO toDto(Attendance entity) {
        return modelMapper.map(entity, AttendanceResponseDTO.class);
    }

    private Attendance toEntity(AttendanceRequestDTO dto) {
        Attendance entity = modelMapper.map(dto, Attendance.class);

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

    @GetMapping
    public ResponseEntity<List<AttendanceResponseDTO>> findAll() {
        List<Attendance> list = attendanceService.findAll();
        List<AttendanceResponseDTO> dtos = list.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceResponseDTO> findById(@PathVariable Long id) {
        Attendance entity = attendanceService.findById(id);
        return ResponseEntity.ok(toDto(entity));
    }

    // Adicionado @Valid
    @PostMapping
    public ResponseEntity<AttendanceResponseDTO> save(@RequestBody @Valid AttendanceRequestDTO dto) {
        Attendance entity = toEntity(dto);
        Attendance saved = attendanceService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(saved));
    }

    // Adicionado @Valid
    @PutMapping("/{id}")
    public ResponseEntity<AttendanceResponseDTO> update(@PathVariable Long id, @RequestBody @Valid AttendanceRequestDTO dto) {
        Attendance entity = toEntity(dto);
        entity.setId(id);
        Attendance updated = attendanceService.save(entity);
        return ResponseEntity.ok(toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        attendanceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}