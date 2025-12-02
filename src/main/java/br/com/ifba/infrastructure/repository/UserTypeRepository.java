package br.com.ifba.infrastructure.repository;

import br.com.ifba.infrastructure.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {

    List<UserType> findByTypeName(String typeName);

    List<UserType> findByDescription(String description);
}