package br.com.ifba.infrastructure.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import br.com.ifba.infrastructure.entity.FinancialMovement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/financial-movements")
@CrossOrigin(origins = "http://localhost:3000")
public class FinancialMovementController {

    private final List<FinancialMovement> movements = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @GetMapping
    public ResponseEntity<List<FinancialMovement>> getAllMovements() {
        return ResponseEntity.ok(movements);
    }

    @PostMapping
    public ResponseEntity<FinancialMovement> createMovement(@RequestBody FinancialMovement movement) {
        movement.setId(idCounter.getAndIncrement());
        // Simula status inicial
        // movement.setStatus(FinancialStatus.PENDENTE);
        movements.add(movement);
        return ResponseEntity.status(201).body(movement);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<FinancialMovement> approveMovement(@PathVariable Long id) {
        Optional<FinancialMovement> movOpt = movements.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();

        if (movOpt.isPresent()) {
            FinancialMovement mov = movOpt.get();
            // Simula aprovação
            // mov.setStatus(FinancialStatus.APROVADO);
            return ResponseEntity.ok(mov);
        }
        return ResponseEntity.notFound().build();
    }
}
