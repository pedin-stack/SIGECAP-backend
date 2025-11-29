package br.com.ifba.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import br.com.ifba.infrastructure.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //  evitar cadastro duplicado
    boolean existsByEmail(String email);

    // para o login (Spring Security)
    Optional<User> findByEmail(String email);


    // Buscar usuários ativos
    List<User> findByIsActiveTrue();
}