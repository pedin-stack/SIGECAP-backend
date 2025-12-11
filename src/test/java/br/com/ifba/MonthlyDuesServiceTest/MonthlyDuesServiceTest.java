package br.com.ifba.MonthlyDuesServiceTest;

import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.infrastructure.role.ExceptionnRole;
import br.com.ifba.monthlydues.repository.MonthlyDuesRepository;
import br.com.ifba.monthlydues.service.MonthlyDuesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class MonthlyDuesServiceTest {

    @InjectMocks
    private MonthlyDuesService monthlyDuesService;

    @Mock
    private MonthlyDuesRepository monthlyDuesRepository;

    @Test
    void shouldThrowExceptionWhenMonthlyDuesNotFound() {
        Mockito.when(monthlyDuesRepository.findById(1L)).thenReturn(Optional.empty());

        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
            monthlyDuesService.findById(1L);
        });

        Assertions.assertEquals(ExceptionnRole .MONTHLY_DUES_NOT_FOUND.getMessage(), exception.getMessage());
    }
}
