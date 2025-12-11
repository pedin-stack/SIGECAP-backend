package br.com.ifba.user.repository;

import br.com.ifba.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findByIsactive(boolean isactive);

    List<User> findByUserTypeId(Long userTypeId);
}