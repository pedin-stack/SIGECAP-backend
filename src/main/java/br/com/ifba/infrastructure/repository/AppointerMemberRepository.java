package br.com.ifba.infrastructure.repository;

import br.com.ifba.infrastructure.entity.AppointerMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointerMemberRepository extends JpaRepository<AppointerMember, Long> {

}