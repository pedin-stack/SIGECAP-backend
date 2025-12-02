package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.entity.Appointer;
import br.com.ifba.infrastructure.role.StatusRole;
import br.com.ifba.infrastructure.role.DeMolayRole;
import br.com.ifba.infrastructure.service.AppointerService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/appointers") // Define a rota base
@CrossOrigin(origins = "http://localhost:3000") // Permite integração com o React
public class AppointerController {

    private final AppointerService appointerService;

    //  listar todos
    @GetMapping
    public ResponseEntity<List<Appointer>> findAll() {
        return ResponseEntity.ok(appointerService.findAll());
    }

    // buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Appointer> findById(@PathVariable Long id) {
        return ResponseEntity.ok(appointerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Appointer> save(@RequestBody Appointer appointer) {
        Appointer savedAppointer = appointerService.save(appointer);
        // Retorna Status 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAppointer);
    }

    // Slide 15: @PutMapping para atualizar recursos
    @PutMapping("/{id}")
    public ResponseEntity<Appointer> update(@PathVariable Long id, @RequestBody Appointer appointer) {
        // Garante que o ID do corpo da requisição seja o mesmo da URL
        appointer.setId(id);
        Appointer updatedAppointer = appointerService.save(appointer);
        return ResponseEntity.ok(updatedAppointer);
    }

    // retorno NO_CONTENT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appointerService.delete(id);
        // Retorna 204 No Content (Sucesso sem corpo de resposta)
        return ResponseEntity.noContent().build();
    }

    // Endpoint para atualizar apenas o status (ex: ATIVA para HISTORICO)
    @PutMapping("/{id}/status")
    public ResponseEntity<Appointer> updateStatus(@PathVariable Long id, @RequestBody StatusRole newStatus) {
        return ResponseEntity.ok(appointerService.updateStatus(id, newStatus));
    }

    // Buscar por Data de Início
    @GetMapping("/search/start-date")
    public ResponseEntity<List<Appointer>> findByStartDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(appointerService.findByStartDate(date));
    }

    // Buscar por Data de Término
    @GetMapping("/search/end-date")
    public ResponseEntity<List<Appointer>> findByEndDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(appointerService.findByEndDate(date));
    }

    // Buscar onde um membro teve um cargo específico
    @GetMapping("/search/member-role")
    public ResponseEntity<List<Appointer>> findByMemberAndRole(
            @RequestParam("userId") Long userId,
            @RequestParam("role") DeMolayRole role) {
        return ResponseEntity.ok(appointerService.findByMemberAndRole(userId, role));
    }
}