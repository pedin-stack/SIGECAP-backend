package br.com.ifba.appointerservice;

import br.com.ifba.appointer.entity.Appointer;
import br.com.ifba.appointer.repository.AppointerRepository;
import br.com.ifba.appointer.service.AppointerService;
import br.com.ifba.infrastructure.exception.BusinessException;

import br.com.ifba.infrastructure.role.ExceptionnRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class AppointerServiceTest {

    @InjectMocks
    private AppointerService appointerService;

    @Mock
    private AppointerRepository appointerRepository;

    @Test
    void shouldThrowExceptionWhenStartDateIsAfterEndDate() {
        // Cenário: Datas inconsistentes
        Appointer appointer = new Appointer();
        appointer.setStartDate(LocalDate.of(2025, 12, 31)); // Fim do ano
        appointer.setEndDate(LocalDate.of(2025, 1, 1));     // Começo do ano (Errado!)

        // Ação e Verificação
        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
            appointerService.save(appointer);
        });

        Assertions.assertEquals(ExceptionnRole.APPOINTER_DATE_ERROR.getMessage(), exception.getMessage());
    }
}