package br.com.ifba.appointermember.repository;

import br.com.ifba.appointermember.entity.AppointerMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointerMemberRepository extends JpaRepository<AppointerMember, Long> {

}