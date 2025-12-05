package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.dto.UserRequestDTO;
import br.com.ifba.infrastructure.dto.UserResponseDTO;
import br.com.ifba.infrastructure.entity.User;
import br.com.ifba.infrastructure.service.UserService;
import jakarta.validation.Valid; // <--- Importante
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
    private final ModelMapper modelMapper;

    private UserResponseDTO toDto(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }

    private User toEntity(UserRequestDTO dto) {
        return modelMapper.map(dto, User.class);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<User> users = userService.findAll();
        List<UserResponseDTO> dtos = users.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(toDto(user));
    }

    // Adicionado @Valid
    @PostMapping
    public ResponseEntity<UserResponseDTO> save(@RequestBody @Valid UserRequestDTO dto) {
        User userEntity = toEntity(dto);
        User savedUser = userService.save(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(savedUser));
    }

    // Adicionado @Valid
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody @Valid UserRequestDTO dto) {
        User userEntity = toEntity(dto);
        userEntity.setId(id);
        User updatedUser = userService.save(userEntity);
        return ResponseEntity.ok(toDto(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

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