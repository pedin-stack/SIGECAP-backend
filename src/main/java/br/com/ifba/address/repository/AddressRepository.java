package br.com.ifba.address.repository;

import br.com.ifba.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    // O JpaRepository já fornece: save(), findAll(), findById(), delete(), etc.
}