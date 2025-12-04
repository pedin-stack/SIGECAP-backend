package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.dto.AppointerMemberRequestDTO;
import br.com.ifba.infrastructure.dto.AppointerMemberResponseDTO;
import br.com.ifba.infrastructure.entity.Appointer;
import br.com.ifba.infrastructure.entity.AppointerMember;
import br.com.ifba.infrastructure.entity.User;
import br.com.ifba.infrastructure.service.AppointerMemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/appointer-members")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointerMemberController {

    private final AppointerMemberService appointerMemberService;
    private final ModelMapper modelMapper;

    // =================================================================================
    // CONVERSORES (DTO <-> Entity)
    // =================================================================================

    private AppointerMemberResponseDTO toDto(AppointerMember entity) {
        // O ModelMapper é inteligente para mapear user.name -> userName
        // e appointer.description -> appointerDescription automaticamente
        return modelMapper.map(entity, AppointerMemberResponseDTO.class);
    }

    private AppointerMember toEntity(AppointerMemberRequestDTO dto) {
        // Aqui temos um detalhe importante: O ModelMapper as vezes se perde
        // tentando converter "userId" (Long) para "User" (Objeto).
        // Para garantir, fazemos o mapeamento básico e setamos os IDs manualmente nos objetos.

        AppointerMember entity = modelMapper.map(dto, AppointerMember.class);

        // Configurando o User (apenas ID para o Service buscar ou salvar referência)
        User userStub = new User();
        userStub.setId(dto.getUserId());
        entity.setUser(userStub);

        // Configurando o Appointer (apenas ID)
        Appointer appointerStub = new Appointer();
        appointerStub.setId(dto.getAppointerId());
        entity.setAppointer(appointerStub);

        return entity;
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<AppointerMemberResponseDTO>> findAll() {
        List<AppointerMember> list = appointerMemberService.findAll();
        List<AppointerMemberResponseDTO> dtos = list.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<AppointerMemberResponseDTO> findById(@PathVariable Long id) {
        AppointerMember entity = appointerMemberService.findById(id);
        return ResponseEntity.ok(toDto(entity));
    }

    // Criar (Atribuir cargo)
    @PostMapping
    public ResponseEntity<AppointerMemberResponseDTO> save(@RequestBody AppointerMemberRequestDTO dto) {
        AppointerMember entity = toEntity(dto);
        AppointerMember saved = appointerMemberService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(saved));
    }

    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<AppointerMemberResponseDTO> update(@PathVariable Long id, @RequestBody AppointerMemberRequestDTO dto) {
        AppointerMember entity = toEntity(dto);
        entity.setId(id); // Garante o ID da URL

        AppointerMember updated = appointerMemberService.save(entity);
        return ResponseEntity.ok(toDto(updated));
    }

    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appointerMemberService.delete(id);
        return ResponseEntity.noContent().build();
    }
}