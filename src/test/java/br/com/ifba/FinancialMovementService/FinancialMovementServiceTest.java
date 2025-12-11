package br.com.ifba.FinancialMovementService;

import br.com.ifba.financialmovement.repository.FinancialMovementRepository;
import br.com.ifba.financialmovement.service.FinancialMovementService;
import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.infrastructure.role.ExceptionnRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FinancialMovementServiceTest {

    @InjectMocks
    private FinancialMovementService service;

    @Mock
    private FinancialMovementRepository repository;

    @Test
    void shouldThrowExceptionWhenDeletingNonExistentId() {
        Mockito.when(repository.existsById(1L)).thenReturn(false);

        BusinessException ex = Assertions.assertThrows(BusinessException.class, () -> service.delete(1L));
        Assertions.assertEquals(ExceptionnRole.FINANCIAL_MOVEMENT_NOT_FOUND.getMessage(), ex.getMessage());
    }
}
