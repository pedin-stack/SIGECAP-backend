package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.entity.UserType;
import br.com.ifba.infrastructure.service.UserTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-types")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class UserTypeController {

    private final UserTypeService userTypeService;


    // GET - Listar Todos
    @GetMapping
    public ResponseEntity<List<UserType>> findAll() {
        return ResponseEntity.ok(userTypeService.findAll());
    }

    // GET - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserType> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userTypeService.findById(id));
    }

    // POST - Criar (Status 201)
    @PostMapping
    public ResponseEntity<UserType> save(@RequestBody UserType userType) {
        UserType savedType = userTypeService.save(userType);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedType);
    }

    // PUT - Atualizar (Status 200)
    @PutMapping("/{id}")
    public ResponseEntity<UserType> update(@PathVariable Long id, @RequestBody UserType userType) {
        userType.setId(id);
        UserType updatedType = userTypeService.save(userType);
        return ResponseEntity.ok(updatedType);
    }

    // DELETE - Deletar (Status 204)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // URL: GET /api/user-types/search/typeName?typeName=ADMIN
    @GetMapping("/search/typeName")
    public ResponseEntity<List<UserType>> findByTypeName(@RequestParam("typeName") String typeName) {
        return ResponseEntity.ok(userTypeService.findByTypeName(typeName));
    }

    // URL: GET /api/user-types/search/description?description=Administrador do Sistema
    @GetMapping("/search/description")
    public ResponseEntity<List<UserType>> findByDescription(@RequestParam("description") String description) {
        return ResponseEntity.ok(userTypeService.findByDescription(description));
    }
}