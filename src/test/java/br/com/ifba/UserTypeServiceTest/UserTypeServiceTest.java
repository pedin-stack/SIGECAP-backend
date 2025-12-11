package br.com.ifba.UserTypeServiceTest;

import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.infrastructure.role.ExceptionnRole;
import br.com.ifba.usertype.repository.UserTypeRepository;
import br.com.ifba.usertype.service.UserTypeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserTypeServiceTest {

    @InjectMocks
    private UserTypeService userTypeService;

    @Mock
    private UserTypeRepository userTypeRepository;

    @Test
    void shouldThrowExceptionWhenUserTypeNotFound() {
        Mockito.when(userTypeRepository.findById(5L)).thenReturn(Optional.empty());

        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
            userTypeService.findById(5L);
        });

        Assertions.assertEquals(ExceptionnRole.USER_TYPE_NOT_FOUND.getMessage(), exception.getMessage());
    }
}