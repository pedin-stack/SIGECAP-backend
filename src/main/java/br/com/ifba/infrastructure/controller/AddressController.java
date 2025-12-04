package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.dto.AddressRequestDTO;
import br.com.ifba.infrastructure.dto.AddressResponseDTO;
import br.com.ifba.infrastructure.entity.Address;
import br.com.ifba.infrastructure.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/addresses")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private final ModelMapper modelMapper; // Injeção do Mapper

    private AddressResponseDTO toDto(Address address) {
        return modelMapper.map(address, AddressResponseDTO.class);
    }

    private Address toEntity(AddressRequestDTO dto) {
        return modelMapper.map(dto, Address.class);
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<AddressResponseDTO>> findAll() {
        List<Address> addresses = addressService.findAll();

        // Converte a lista de Entidades para lista de DTOs
        List<AddressResponseDTO> dtos = addresses.stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> findById(@PathVariable Long id) {
        Address address = addressService.findById(id);
        return ResponseEntity.ok(toDto(address));
    }

    // Criar
    @PostMapping
    public ResponseEntity<AddressResponseDTO> save(@RequestBody AddressRequestDTO dto) {
        // 1. DTO -> Entity
        Address addressEntity = toEntity(dto);

        // 2. Service salva
        Address savedAddress = addressService.save(addressEntity);

        // 3. Entity -> DTO
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(savedAddress));
    }

    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> update(@PathVariable Long id, @RequestBody AddressRequestDTO dto) {
        Address addressEntity = toEntity(dto);

        // Opção A: Se o seu service for: update(Long id, Address entity)
        Address updatedAddress = addressService.update(id, addressEntity);

        return ResponseEntity.ok(toDto(updatedAddress));
    }

    // Deletar (Não muda nada, pois não tem corpo)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}