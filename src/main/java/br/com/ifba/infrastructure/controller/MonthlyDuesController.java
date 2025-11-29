package br.com.ifba.infrastructure.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import br.com.ifba.infrastructure.entity.MonthlyDues;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/monthly-dues")
@CrossOrigin(origins = "http://localhost:3000")
public class MonthlyDuesController {

    private final List<MonthlyDues> duesList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MonthlyDues>> getDuesByUser(@PathVariable Long userId) {
        // Filtra na lista em memória pelo ID do usuário (simulado)
        List<MonthlyDues> userDues = duesList.stream()
                .filter(d -> d.getMember() != null && d.getMember().getId().equals(userId))
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDues);
    }

    @PostMapping
    public ResponseEntity<MonthlyDues> createDue(@RequestBody MonthlyDues due) {
        due.setId(idCounter.getAndIncrement());
        // due.setStatus(DuesStatus.PENDENTE);
        duesList.add(due);
        return ResponseEntity.status(201).body(due);
    }

    @PutMapping("/{id}/pay")
    public ResponseEntity<MonthlyDues> registerPayment(@PathVariable Long id) {
        Optional<MonthlyDues> dueOpt = duesList.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst();

        if (dueOpt.isPresent()) {
            MonthlyDues due = dueOpt.get();
            // due.setStatus(DuesStatus.PAGO);
            return ResponseEntity.ok(due);
        }
        return ResponseEntity.notFound().build();
    }
}