package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.entity.Address;
import br.com.ifba.infrastructure.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/addresses") // Define a rota base no plural
@CrossOrigin(origins = "http://localhost:3000") // Permite acesso do React
public class AddressController {

    private final AddressService addressService;

    //  listar todos os endereços
    @GetMapping
    public ResponseEntity<List<Address>> findAll() {
        return ResponseEntity.ok(addressService.findAll());
    }

    //  buscar um endereço específico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Address> findById(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.findById(id));
    }

    //  criar um novo endereço
    @PostMapping
    public ResponseEntity<Address> save(@RequestBody Address address) {
        Address savedAddress = addressService.save(address);
        // Retorna Status 201 (Created) indicando que o recurso foi criado com sucesso
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);
    }

    // atualizar um endereço existente
    @PutMapping("/{id}")
    public ResponseEntity<Address> update(@PathVariable Long id, @RequestBody Address address) {

        Address updatedAddress = addressService.update(id, address);
        // Retorna 200 OK com o objeto atualizado
        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        addressService.delete(id);
        // Retorna 204 No Content (Sucesso, sem corpo de resposta)
        return ResponseEntity.noContent().build();
    }
}