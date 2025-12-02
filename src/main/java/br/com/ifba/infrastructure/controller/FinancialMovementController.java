package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.entity.FinancialMovement;
import br.com.ifba.infrastructure.service.FinancialMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/financial-movements")
@CrossOrigin(origins = "http://localhost:3000")
public class FinancialMovementController {

    private final FinancialMovementService financialMovementService;

    @GetMapping
    public ResponseEntity<List<FinancialMovement>> findAll() {
        return ResponseEntity.ok(financialMovementService.findAll());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<FinancialMovement> findById(@PathVariable Long id) {
        return ResponseEntity.ok(financialMovementService.findById(id));
    }

    //Criar (Status 201 Created)
    @PostMapping
    public ResponseEntity<FinancialMovement> save(@RequestBody FinancialMovement movement) {
        FinancialMovement savedMovement = financialMovementService.save(movement);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovement);
    }

    // Atualizar (Status 200 OK)
    @PutMapping("/{id}")
    public ResponseEntity<FinancialMovement> update(@PathVariable Long id, @RequestBody FinancialMovement movement) {
        movement.setId(id);
        FinancialMovement updatedMovement = financialMovementService.save(movement);
        return ResponseEntity.ok(updatedMovement);
    }

    // DELETE - Deletar (Status 204 No Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        financialMovementService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar por Dat
    @GetMapping("/search/date")
    public ResponseEntity<List<FinancialMovement>> findByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return ResponseEntity.ok(financialMovementService.findByDate(date));
    }

    // Buscar por Valor
    @GetMapping("/search/value")
    public ResponseEntity<List<FinancialMovement>> findByValue(@RequestParam("value") double value) {
        return ResponseEntity.ok(financialMovementService.findByValue(value));
    }
}