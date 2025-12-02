package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.entity.AppointerMember;
import br.com.ifba.infrastructure.service.AppointerMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/appointer-members") // Rota no plural e separada por hífen (padrão REST)
@CrossOrigin(origins = "http://localhost:3000") // Permite acesso do React
public class AppointerMemberController {

    private final AppointerMemberService appointerMemberService;

    //  @GetMapping para listar todos
    @GetMapping
    public ResponseEntity<List<AppointerMember>> findAll() {
        return ResponseEntity.ok(appointerMemberService.findAll());
    }

    // @GetMapping com @PathVariable para buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<AppointerMember> findById(@PathVariable Long id) {
        return ResponseEntity.ok(appointerMemberService.findById(id));
    }

    //criar (Atribuir cargo a membro)
    @PostMapping
    public ResponseEntity<AppointerMember> save(@RequestBody AppointerMember appointerMember) {
        AppointerMember savedMember = appointerMemberService.save(appointerMember);
        // Retorna Status 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMember);
    }

    // atualizar (Ex: Trocar o cargo ou a gestão de um registro existente)
    @PutMapping("/{id}")
    public ResponseEntity<AppointerMember> update(@PathVariable Long id, @RequestBody AppointerMember appointerMember) {
        // Garante que o ID da URL seja respeitado
        appointerMember.setId(id);
        AppointerMember updatedMember = appointerMemberService.save(appointerMember);
        // Retorna 200 OK
        return ResponseEntity.ok(updatedMember);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appointerMemberService.delete(id);
        return ResponseEntity.noContent().build();
    }
}