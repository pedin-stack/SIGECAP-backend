package br.com.ifba.infrastructure.service;

import br.com.ifba.infrastructure.entity.Address;
import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.infrastructure.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j //para adicionar o log depois
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    @Transactional
    public Address save(Address address) {
        if (address.getCep() == null || address.getCep().isEmpty()) {
            throw new RuntimeException("O CEP é obrigatório.");
        }
        return addressRepository.save(address);
    }

    public Page<Address> findAll(Pageable pageable) {
        return addressRepository.findAll(pageable);
    }

    public Address findById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Endereço não encontrado com ID: " + id));
    }


    @Transactional
    public Address update(Long id, Address addressUpdated) {

        Address existingAddress = findById(id);

        existingAddress.setStreet(addressUpdated.getStreet());
        existingAddress.setNumber(addressUpdated.getNumber());
        existingAddress.setNeighborhood(addressUpdated.getNeighborhood());
        existingAddress.setCity(addressUpdated.getCity());
        existingAddress.setState(addressUpdated.getState());
        existingAddress.setCep(addressUpdated.getCep());

        return addressRepository.save(existingAddress);
    }

    @Transactional
    public void delete(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new BusinessException("Endereço não encontrado para exclusão.");
        }
        addressRepository.deleteById(id);
    }
}