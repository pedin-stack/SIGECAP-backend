package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.entity.Objective;
import br.com.ifba.infrastructure.service.ObjectiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/objectives")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor // Injeção de dependência via Lombok
public class ObjectiveController {

    private final ObjectiveService objectiveService;

    // Listar Todos
    @GetMapping
    public ResponseEntity<List<Objective>> findAll() {
        return ResponseEntity.ok(objectiveService.findAll());
    }

    //Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Objective> findById(@PathVariable Long id) {
        return ResponseEntity.ok(objectiveService.findById(id));
    }

    //  Criar (Status 201 Created)
    @PostMapping
    public ResponseEntity<Objective> save(@RequestBody Objective objective) {
        Objective savedObjective = objectiveService.save(objective);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedObjective);
    }

    // Atualizar (Status 200 OK)
    @PutMapping("/{id}")
    public ResponseEntity<Objective> update(@PathVariable Long id, @RequestBody Objective objective) {
        // Garante que o ID da URL seja respeitado no objeto
        objective.setId(id);
        Objective updatedObjective = objectiveService.save(objective);
        return ResponseEntity.ok(updatedObjective);
    }

    // DELETE - Deletar (Status 204 No Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        objectiveService.delete(id);
        return ResponseEntity.noContent().build();
    }
}