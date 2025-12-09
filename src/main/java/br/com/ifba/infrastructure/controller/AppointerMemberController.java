package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.dto.AppointerMemberRequestDTO;
import br.com.ifba.infrastructure.dto.AppointerMemberResponseDTO;
import br.com.ifba.infrastructure.entity.Appointer;
import br.com.ifba.infrastructure.entity.AppointerMember;
import br.com.ifba.infrastructure.entity.User;
import br.com.ifba.infrastructure.service.AppointerMemberService;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/appointer-members")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointerMemberController {

    private final AppointerMemberService appointerMemberService;
    private final ModelMapper modelMapper;

    private AppointerMemberResponseDTO toDto(AppointerMember entity) {
        return modelMapper.map(entity, AppointerMemberResponseDTO.class);
    }

    private AppointerMember toEntity(AppointerMemberRequestDTO dto) {
        AppointerMember entity = modelMapper.map(dto, AppointerMember.class);

        User userStub = new User();
        userStub.setId(dto.getUserId());
        entity.setUser(userStub);

        Appointer appointerStub = new Appointer();
        appointerStub.setId(dto.getAppointerId());
        entity.setAppointer(appointerStub);

        return entity;
    }

    @GetMapping
    public ResponseEntity<Page<AppointerMemberResponseDTO>> findAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(appointerMemberService.findAll(pageable).map(this::toDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointerMemberResponseDTO> findById(@PathVariable Long id) {
        AppointerMember entity = appointerMemberService.findById(id);
        return ResponseEntity.ok(toDto(entity));
    }

    // Adicionado @Valid
    @PostMapping
    public ResponseEntity<AppointerMemberResponseDTO> save(@RequestBody @Valid AppointerMemberRequestDTO dto) {
        AppointerMember entity = toEntity(dto);
        AppointerMember saved = appointerMemberService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(saved));
    }

    // Adicionado @Valid
    @PutMapping("/{id}")
    public ResponseEntity<AppointerMemberResponseDTO> update(@PathVariable Long id, @RequestBody @Valid AppointerMemberRequestDTO dto) {
        AppointerMember entity = toEntity(dto);
        entity.setId(id);
        AppointerMember updated = appointerMemberService.save(entity);
        return ResponseEntity.ok(toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appointerMemberService.delete(id);
        return ResponseEntity.noContent().build();
    }
}