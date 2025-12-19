package br.com.ifba.appointer.repository;

import br.com.ifba.appointer.entity.Appointer;
import br.com.ifba.infrastructure.role.occupationRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointerRepository extends JpaRepository<Appointer, Long> {

    List<Appointer> findByStartDate(LocalDate startDate);

    List<Appointer> findByEndDate(LocalDate endDate);

    /* 3. Encontrar uma Nominata onde um membro específico teve um cargo específico
     Explicação: Selecione a Nominata (a) juntando com seus membros (m)
     onde o ID do usuário é X e o Cargo é Y.*/
    @Query("SELECT a FROM Appointer a JOIN a.members m WHERE m.user.id = :userId AND m.role = :role")
    List<Appointer> findByMemberAndRole(@Param("userId") Long userId,
                                        @Param("role") occupationRole role);
}