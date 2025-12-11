package br.com.ifba.ObjectiveServiceTest;

import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.infrastructure.role.ExceptionnRole;
import br.com.ifba.objective.repository.ObjectiveRepository;
import br.com.ifba.objective.service.ObjectiveService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ObjectiveServiceTest {

    @InjectMocks
    private ObjectiveService objectiveService;

    @Mock
    private ObjectiveRepository objectiveRepository;

    @Test
    void shouldThrowExceptionWhenDeletingNonExistentObjective() {
        Mockito.when(objectiveRepository.existsById(99L)).thenReturn(false);

        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
            objectiveService.delete(99L);
        });

        Assertions.assertEquals(ExceptionnRole.OBJECTIVE_NOT_FOUND.getMessage(), exception.getMessage());
    }
}