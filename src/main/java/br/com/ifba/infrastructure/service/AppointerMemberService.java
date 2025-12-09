package br.com.ifba.infrastructure.service;

import br.com.ifba.infrastructure.entity.AppointerMember;
import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.infrastructure.repository.AppointerMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AppointerMemberService {

    private final AppointerMemberRepository appointerMemberRepository;

    public Page<AppointerMember> findAll(Pageable pageable) {
        return appointerMemberRepository.findAll(pageable);
    }

    public AppointerMember findById(Long id) {
        return appointerMemberRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Registro de membro não encontrado com ID: " + id));
    }

    @Transactional
    public AppointerMember save(AppointerMember member) {
        if (member.getUser() == null || member.getUser().getId() == null) {
            throw new BusinessException("É obrigatório informar o Usuário.");
        }
        if (member.getAppointer() == null || member.getAppointer().getId() == null) {
            throw new BusinessException("É obrigatório informar a Nominata.");
        }
        return appointerMemberRepository.save(member);
    }

    @Transactional
    public void delete(Long id) {
        if (!appointerMemberRepository.existsById(id)) {
            throw new BusinessException("Registro não encontrado para exclusão.");
        }
        appointerMemberRepository.deleteById(id);
    }
}