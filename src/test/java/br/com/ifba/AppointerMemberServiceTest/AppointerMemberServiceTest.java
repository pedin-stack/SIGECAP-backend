package br.com.ifba.AppointerMemberServiceTest;

import br.com.ifba.appointermember.entity.AppointerMember;
import br.com.ifba.appointermember.repository.AppointerMemberRepository;
import br.com.ifba.appointermember.service.AppointerMemberService;

import br.com.ifba.infrastructure.exception.BusinessException;

import br.com.ifba.infrastructure.role.ExceptionnRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AppointerMemberServiceTest {

    @InjectMocks
    private AppointerMemberService service;

    @Mock
    private AppointerMemberRepository repository;

    @Test
    void shouldThrowExceptionWhenUserIsNull() {
        AppointerMember member = new AppointerMember();
        member.setUser(null); // Erro!

        BusinessException ex = Assertions.assertThrows(BusinessException.class, () -> service.save(member));
        Assertions.assertEquals(ExceptionnRole.MEMBER_MANDATORY.getMessage(), ex.getMessage());
    }
}