package br.com.ifba.userservice;

import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.infrastructure.role.ExceptionnRole;
import br.com.ifba.user.repository.UserRepository;
import br.com.ifba.user.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class) // Inicializa os Mocks
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository; // Dependência mockada (falsa)

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        // Cenário: Repository retorna Vazio (Optional.empty) ao buscar ID 99
        Mockito.when(userRepository.findById(99L)).thenReturn(Optional.empty());

        // Ação e Verificação: O Service deve lançar BusinessException
        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
            userService.findById(99L);
        });

        // Valida se a mensagem é a esperada (do Enum que criamos)
        Assertions.assertEquals(ExceptionnRole.USER_NOT_FOUND.getMessage(), exception.getMessage());
    }
}