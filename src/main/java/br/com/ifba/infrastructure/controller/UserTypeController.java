package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.dto.UserTypeRequestDTO;
import br.com.ifba.infrastructure.dto.UserTypeResponseDTO;
import br.com.ifba.infrastructure.entity.UserType;
import br.com.ifba.infrastructure.service.UserTypeService;
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
@RequestMapping("/api/user-types")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class UserTypeController {

    private final UserTypeService userTypeService;
    private final ModelMapper modelMapper;

    private UserTypeResponseDTO toDto(UserType entity) {
        return modelMapper.map(entity, UserTypeResponseDTO.class);
    }

    private UserType toEntity(UserTypeRequestDTO dto) {
        return modelMapper.map(dto, UserType.class);
    }

    @GetMapping
    public ResponseEntity<Page<UserTypeResponseDTO>> findAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(userTypeService.findAll(pageable).map(this::toDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserTypeResponseDTO> findById(@PathVariable Long id) {
        UserType entity = userTypeService.findById(id);
        return ResponseEntity.ok(toDto(entity));
    }

    // Adicionado @Valid
    @PostMapping
    public ResponseEntity<UserTypeResponseDTO> save(@RequestBody @Valid UserTypeRequestDTO dto) {
        UserType entity = toEntity(dto);
        UserType saved = userTypeService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(saved));
    }

    // Adicionado @Valid
    @PutMapping("/{id}")
    public ResponseEntity<UserTypeResponseDTO> update(@PathVariable Long id, @RequestBody @Valid UserTypeRequestDTO dto) {
        UserType entity = toEntity(dto);
        entity.setId(id);
        UserType updated = userTypeService.save(entity);
        return ResponseEntity.ok(toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/typeName")
    public ResponseEntity<List<UserTypeResponseDTO>> findByTypeName(@RequestParam("typeName") String typeName) {
        List<UserType> list = userTypeService.findByTypeName(typeName);
        List<UserTypeResponseDTO> dtos = list.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/search/description")
    public ResponseEntity<List<UserTypeResponseDTO>> findByDescription(@RequestParam("description") String description) {
        List<UserType> list = userTypeService.findByDescription(description);
        List<UserTypeResponseDTO> dtos = list.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}