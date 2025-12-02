package br.com.ifba.infrastructure.service;

import br.com.ifba.infrastructure.entity.AppointerMember;
import br.com.ifba.infrastructure.repository.AppointerMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AppointerMemberService {

    private final AppointerMemberRepository appointerMemberRepository;

    public List<AppointerMember> findAll() {
        return appointerMemberRepository.findAll();
    }

    public AppointerMember findById(Long id) {
        return appointerMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de membro em cargo não encontrado com ID: " + id));
    }

    @Transactional
    public AppointerMember save(AppointerMember appointerMember) {

        if (appointerMember.getUser() == null || appointerMember.getUser().getId() == null) {
            throw new RuntimeException("É obrigatório informar o Usuário para atribuir um cargo.");
        }

        if (appointerMember.getAppointer() == null || appointerMember.getAppointer().getId() == null) {
            throw new RuntimeException("É obrigatório informar a Nominata (Gestão) para atribuir um cargo.");
        }

        if (appointerMember.getRole() == null) {
            throw new RuntimeException("É obrigatório informar qual o cargo (Role) do membro.");
        }

        return appointerMemberRepository.save(appointerMember);
    }

    @Transactional
    public void delete(Long id) {
        if (!appointerMemberRepository.existsById(id)) {
            throw new RuntimeException("Registro não encontrado para exclusão.");
        }
        appointerMemberRepository.deleteById(id);
    }
}