package br.com.ifba.infrastructure.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import br.com.ifba.infrastructure.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    // Simula o Banco de Dados em Memória
    private final List<User> users = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setId(idCounter.getAndIncrement()); // Gera ID automático
        // Simula regra básica: Ativar usuário ao criar
        user.setActive(true);
        users.add(user);
        return ResponseEntity.status(201).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userUpdated) {
        Optional<User> existingUserOpt = users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();

        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            // Atualiza campos (Exemplo simples)
            existingUser.setEmail(userUpdated.getEmail());
            existingUser.setPassword(userUpdated.getPassword());
            existingUser.setName(userUpdated.getName());
            return ResponseEntity.ok(existingUser);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        users.removeIf(u -> u.getId().equals(id));
        return ResponseEntity.noContent().build();
    }
}