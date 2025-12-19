package br.com.ifba.usertype.repository;

import br.com.ifba.usertype.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {

    List<UserType> findByDescription(String typeName);

    List<UserType> findByOccupation(String description);
}