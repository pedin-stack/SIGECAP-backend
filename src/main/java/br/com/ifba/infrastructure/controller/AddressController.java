package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.dto.AddressRequestDTO;
import br.com.ifba.infrastructure.dto.AddressResponseDTO;
import br.com.ifba.infrastructure.entity.Address;
import br.com.ifba.infrastructure.service.AddressService;
import jakarta.validation.Valid; // <--- Importante
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
    private final ModelMapper modelMapper;

    private AddressResponseDTO toDto(Address address) {
        return modelMapper.map(address, AddressResponseDTO.class);
    }

    private Address toEntity(AddressRequestDTO dto) {
        return modelMapper.map(dto, Address.class);
    }

    @GetMapping
    public ResponseEntity<List<AddressResponseDTO>> findAll() {
        List<Address> addresses = addressService.findAll();
        List<AddressResponseDTO> dtos = addresses.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> findById(@PathVariable Long id) {
        Address address = addressService.findById(id);
        return ResponseEntity.ok(toDto(address));
    }

    // Adicionado @Valid
    @PostMapping
    public ResponseEntity<AddressResponseDTO> save(@RequestBody @Valid AddressRequestDTO dto) {
        Address addressEntity = toEntity(dto);
        Address savedAddress = addressService.save(addressEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(savedAddress));
    }

    // Adicionado @Valid
    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> update(@PathVariable Long id, @RequestBody @Valid AddressRequestDTO dto) {
        Address addressEntity = toEntity(dto);
        // O ID é setado no service ou garantido aqui, dependendo da sua lógica de update no service
        // addressEntity.setId(id); (Se o mapper não setar, o service deve tratar com o ID da URL)

        Address updatedAddress = addressService.update(id, addressEntity);
        return ResponseEntity.ok(toDto(updatedAddress));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}