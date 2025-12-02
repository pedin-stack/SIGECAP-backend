package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.entity.MonthlyDues;
import br.com.ifba.infrastructure.service.MonthlyDuesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monthly-dues")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor // Injeção de dependência limpa via Lombok
public class MonthlyDuesController {

    private final MonthlyDuesService monthlyDuesService;

    //Listar Todos
    @GetMapping
    public ResponseEntity<List<MonthlyDues>> findAll() {
        return ResponseEntity.ok(monthlyDuesService.findAll());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<MonthlyDues> findById(@PathVariable Long id) {
        return ResponseEntity.ok(monthlyDuesService.findById(id));
    }

    // POST - Criar (Status 201)
    @PostMapping
    public ResponseEntity<MonthlyDues> save(@RequestBody MonthlyDues monthlyDues) {
        MonthlyDues savedDues = monthlyDuesService.save(monthlyDues);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDues);
    }

    // PUT - Atualizar (Status 200)
    @PutMapping("/{id}")
    public ResponseEntity<MonthlyDues> update(@PathVariable Long id, @RequestBody MonthlyDues monthlyDues) {
        monthlyDues.setId(id);
        MonthlyDues updatedDues = monthlyDuesService.save(monthlyDues);
        return ResponseEntity.ok(updatedDues);
    }

    // DELETE - Deletar (Status 204)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        monthlyDuesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}