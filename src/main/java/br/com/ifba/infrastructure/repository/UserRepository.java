package br.com.ifba.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import br.com.ifba.infrastructure.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Evitar cadastro duplicado
    boolean existsByEmail(String email);

    // Para o login (Spring Security)
    Optional<User> findByEmail(String email);

    // CORREÇÃO: Buscar usuários ativos (SEM parâmetro)
    List<User> findByActiveTrue();

    // Se precisar buscar por status específico, use:
    // List<User> findByActive(boolean active);
}