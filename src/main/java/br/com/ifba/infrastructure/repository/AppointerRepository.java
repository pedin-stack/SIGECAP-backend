package br.com.ifba.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import br.com.ifba.infrastructure.entity.Appointer;

@Repository
public interface AppointerRepository extends JpaRepository<Appointer, Long> {

    // Encontrar a gestão atualmente ATIVA (Para exibir na Home quem é o MC atual)
    Optional<Appointer> findByStatus(boolean status);

    // Listar todas ordenadas da mais recente para a mais antiga (Histórico)
    List<Appointer> findAllByOrderByStartDateDesc();
}
