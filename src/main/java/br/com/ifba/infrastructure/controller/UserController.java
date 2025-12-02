package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.entity.User;
import br.com.ifba.infrastructure.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor // Injeção automática via Lombok
public class UserController {

    private final UserService userService;

    // GET - Listar Todos
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    // GET - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    //  Criar (Status 201)
    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        User savedUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    // Atualizar (Status 200)
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {

        user.setId(id);
        User updatedUser = userService.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    //eletar (Exclusão Lógica / Soft Delete) - Status 204
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar por Email

    @GetMapping("/search/email")
    public ResponseEntity<User> findByEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    // Buscar por Status (Ativo/Inativo)

    @GetMapping("/search/status")
    public ResponseEntity<List<User>> findByStatus(@RequestParam("active") boolean active) {
        return ResponseEntity.ok(userService.findByStatus(active));
    }

    @GetMapping("/search/type")
    public ResponseEntity<List<User>> findByUserType(@RequestParam("typeId") Long typeId) {
        return ResponseEntity.ok(userService.findByUserType(typeId));
    }
}