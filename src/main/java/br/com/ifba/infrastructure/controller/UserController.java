package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.dto.UserRequestDTO;
import br.com.ifba.infrastructure.dto.UserResponseDTO;
import br.com.ifba.infrastructure.entity.User;
import br.com.ifba.infrastructure.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper; // Injeção do Object Mapper

    // =================================================================================
    // MÉTODOS AUXILIARES DE CONVERSÃO (Para não repetir código)
    // =================================================================================

    private UserResponseDTO toDto(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }

    private User toEntity(UserRequestDTO dto) {
        return modelMapper.map(dto, User.class);
    }

    // =================================================================================
    // CRUD
    // =================================================================================

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        // 1. Busca lista de Entidades
        List<User> users = userService.findAll();

        // 2. Converte cada Entidade para DTO usando Stream
        List<UserResponseDTO> dtos = users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(toDto(user));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> save(@RequestBody UserRequestDTO dto) {
        // 1. Converte DTO -> Entidade
        User userEntity = toEntity(dto);

        // 2. Salva no banco (Lógica de negócio)
        User savedUser = userService.save(userEntity);

        // 3. Converte Entidade Salva -> DTO de Resposta (sem senha)
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(savedUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody UserRequestDTO dto) {
        User userEntity = toEntity(dto);
        userEntity.setId(id); // Garante o ID

        User updatedUser = userService.save(userEntity);
        return ResponseEntity.ok(toDto(updatedUser));
    }

    // O Delete não muda nada, pois não retorna corpo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // =================================================================================
    // BUSCAS ESPECÍFICAS
    // =================================================================================

    @GetMapping("/search/email")
    public ResponseEntity<UserResponseDTO> findByEmail(@RequestParam("email") String email) {
        User user = userService.findByEmail(email);
        return ResponseEntity.ok(toDto(user));
    }

    @GetMapping("/search/status")
    public ResponseEntity<List<UserResponseDTO>> findByStatus(@RequestParam("active") boolean active) {
        List<User> users = userService.findByStatus(active);
        List<UserResponseDTO> dtos = users.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/search/type")
    public ResponseEntity<List<UserResponseDTO>> findByUserType(@RequestParam("typeId") Long typeId) {
        List<User> users = userService.findByUserType(typeId);
        List<UserResponseDTO> dtos = users.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}