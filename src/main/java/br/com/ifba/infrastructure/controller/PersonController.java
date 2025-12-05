package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.dto.PersonRequestDTO;
import br.com.ifba.infrastructure.dto.PersonResponseDTO;
import br.com.ifba.infrastructure.entity.Address;
import br.com.ifba.infrastructure.entity.Person;
import br.com.ifba.infrastructure.service.PersonService;
import jakarta.validation.Valid; // <--- Importante
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/persons")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final ModelMapper modelMapper;

    private PersonResponseDTO toDto(Person entity) {
        return modelMapper.map(entity, PersonResponseDTO.class);
    }

    private Person toEntity(PersonRequestDTO dto) {
        Person entity = modelMapper.map(dto, Person.class);

        if (dto.getAddressId() != null) {
            Address addressStub = new Address();
            addressStub.setId(dto.getAddressId());
            entity.setAddress(addressStub);
        }

        return entity;
    }

    @GetMapping
    public ResponseEntity<List<PersonResponseDTO>> findAll() {
        List<Person> list = personService.findAll();
        List<PersonResponseDTO> dtos = list.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponseDTO> findById(@PathVariable Long id) {
        Person entity = personService.findById(id);
        return ResponseEntity.ok(toDto(entity));
    }

    // Adicionado @Valid
    @PostMapping
    public ResponseEntity<PersonResponseDTO> save(@RequestBody @Valid PersonRequestDTO dto) {
        Person entity = toEntity(dto);
        Person saved = personService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(saved));
    }

    // Adicionado @Valid
    @PutMapping("/{id}")
    public ResponseEntity<PersonResponseDTO> update(@PathVariable Long id, @RequestBody @Valid PersonRequestDTO dto) {
        Person entity = toEntity(dto);
        entity.setId(id);
        Person updated = personService.save(entity);
        return ResponseEntity.ok(toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}