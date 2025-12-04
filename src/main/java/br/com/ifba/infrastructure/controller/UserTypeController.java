package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.dto.UserTypeRequestDTO;
import br.com.ifba.infrastructure.dto.UserTypeResponseDTO;
import br.com.ifba.infrastructure.entity.UserType;
import br.com.ifba.infrastructure.service.UserTypeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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

    // =================================================================================
    // CONVERSORES
    // =================================================================================

    private UserTypeResponseDTO toDto(UserType entity) {
        return modelMapper.map(entity, UserTypeResponseDTO.class);
    }

    private UserType toEntity(UserTypeRequestDTO dto) {
        return modelMapper.map(dto, UserType.class);
    }

    // =================================================================================
    // CRUD
    // =================================================================================

    // GET - Listar Todos
    @GetMapping
    public ResponseEntity<List<UserTypeResponseDTO>> findAll() {
        List<UserType> list = userTypeService.findAll();
        List<UserTypeResponseDTO> dtos = list.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // GET - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserTypeResponseDTO> findById(@PathVariable Long id) {
        UserType entity = userTypeService.findById(id);
        return ResponseEntity.ok(toDto(entity));
    }

    // POST - Criar (Status 201)
    @PostMapping
    public ResponseEntity<UserTypeResponseDTO> save(@RequestBody UserTypeRequestDTO dto) {
        UserType entity = toEntity(dto);
        UserType saved = userTypeService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(saved));
    }

    // PUT - Atualizar (Status 200)
    @PutMapping("/{id}")
    public ResponseEntity<UserTypeResponseDTO> update(@PathVariable Long id, @RequestBody UserTypeRequestDTO dto) {
        UserType entity = toEntity(dto);
        entity.setId(id); // Garante consistência

        UserType updated = userTypeService.save(entity);
        return ResponseEntity.ok(toDto(updated));
    }

    // DELETE - Deletar (Status 204)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // =================================================================================
    // MÉTODOS DE BUSCA ESPECÍFICOS
    // =================================================================================

    // URL: GET /api/user-types/search/typeName?typeName=ADMIN
    @GetMapping("/search/typeName")
    public ResponseEntity<List<UserTypeResponseDTO>> findByTypeName(@RequestParam("typeName") String typeName) {
        List<UserType> list = userTypeService.findByTypeName(typeName);
        List<UserTypeResponseDTO> dtos = list.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // URL: GET /api/user-types/search/description?description=Administrador
    @GetMapping("/search/description")
    public ResponseEntity<List<UserTypeResponseDTO>> findByDescription(@RequestParam("description") String description) {
        List<UserType> list = userTypeService.findByDescription(description);
        List<UserTypeResponseDTO> dtos = list.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}