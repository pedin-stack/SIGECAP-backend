package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.dto.PersonRequestDTO;
import br.com.ifba.infrastructure.dto.PersonResponseDTO;
import br.com.ifba.infrastructure.entity.Address;
import br.com.ifba.infrastructure.entity.Person;
import br.com.ifba.infrastructure.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/persons") // Rota base no plural
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final ModelMapper modelMapper;

    // =================================================================================
    // CONVERSORES (DTO <-> Entity)
    // =================================================================================

    private PersonResponseDTO toDto(Person entity) {
        // O ModelMapper vai converter automaticamente o objeto 'address' da entidade
        // para o 'AddressResponseDTO' do DTO de resposta.
        return modelMapper.map(entity, PersonResponseDTO.class);
    }

    private Person toEntity(PersonRequestDTO dto) {
        Person entity = modelMapper.map(dto, Person.class);

        // Mapeamento manual do relacionamento com Address (Safe Mapping)
        if (dto.getAddressId() != null) {
            Address addressStub = new Address();
            addressStub.setId(dto.getAddressId());
            entity.setAddress(addressStub);
        }

        return entity;
    }

    // =================================================================================
    // CRUD BÁSICO
    // =================================================================================

    // Listar Todos
    @GetMapping
    public ResponseEntity<List<PersonResponseDTO>> findAll() {
        List<Person> list = personService.findAll();

        List<PersonResponseDTO> dtos = list.stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<PersonResponseDTO> findById(@PathVariable Long id) {
        Person entity = personService.findById(id);
        return ResponseEntity.ok(toDto(entity));
    }

    // Criar (Status 201)
    @PostMapping
    public ResponseEntity<PersonResponseDTO> save(@RequestBody PersonRequestDTO dto) {
        Person entity = toEntity(dto);
        Person saved = personService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(saved));
    }

    // Atualizar (Status 200)
    @PutMapping("/{id}")
    public ResponseEntity<PersonResponseDTO> update(@PathVariable Long id, @RequestBody PersonRequestDTO dto) {
        Person entity = toEntity(dto);
        entity.setId(id); // Garante consistência do ID

        Person updated = personService.save(entity);
        return ResponseEntity.ok(toDto(updated));
    }

    // Deletar (Status 204)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}