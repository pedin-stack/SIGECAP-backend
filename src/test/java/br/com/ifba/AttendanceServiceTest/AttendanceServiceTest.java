package br.com.ifba.AttendanceServiceTest;

import br.com.ifba.attendance.repository.AttendanceRepository;
import br.com.ifba.attendance.service.AttendanceService;
import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.infrastructure.role.ExceptionnRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AttendanceServiceTest {

    @InjectMocks
    private AttendanceService attendanceService;

    @Mock
    private AttendanceRepository attendanceRepository;

    @Test
    void shouldThrowExceptionWhenAttendanceNotFound() {
        // Cenário: O repositório não encontra nada com o ID 50
        Mockito.when(attendanceRepository.findById(50L)).thenReturn(Optional.empty());

        // Ação: Tentar buscar deve gerar erro
        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
            attendanceService.findById(50L);
        });

        // Verificação: A mensagem deve ser específica de Presença
        Assertions.assertEquals(ExceptionnRole.ATTENDANCE_NOT_FOUND.getMessage(), exception.getMessage());
    }
}