package br.com.ifba.infrastructure.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import br.com.ifba.infrastructure.entity.Appointer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/appointers")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointerController {

    private final List<Appointer> appointers = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @GetMapping
    public ResponseEntity<List<Appointer>> getAllNominatas() {
        return ResponseEntity.ok(appointers);
    }

    @PostMapping
    public ResponseEntity<Appointer> createNominata(@RequestBody Appointer appointer) {
        appointer.setId(idCounter.getAndIncrement());
        // appointer.setStatus(AppointerStatus.PROPOSTA);
        appointers.add(appointer);
        return ResponseEntity.status(201).body(appointer);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Appointer> updateStatus(@PathVariable Long id, @RequestBody String newStatus) {
        Optional<Appointer> appOpt = appointers.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();

        if (appOpt.isPresent()) {
            // Em um cenário real converteriamos a string para Enum
            // appOpt.get().setStatus(AppointerStatus.valueOf(newStatus));
            return ResponseEntity.ok(appOpt.get());
        }
        return ResponseEntity.notFound().build();
    }
}