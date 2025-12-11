package br.com.ifba.EventServiceTest;

import br.com.ifba.event.repository.EventRepository;
import br.com.ifba.event.service.EventService;
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
class EventServiceTest {

    @InjectMocks
    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    @Test
    void shouldThrowExceptionWhenEventNotFound() {
        Mockito.when(eventRepository.findById(50L)).thenReturn(Optional.empty());

        BusinessException ex = Assertions.assertThrows(BusinessException.class, () -> eventService.findById(50L));

        // Verifique se a mensagem do Enum está correta para Evento
        Assertions.assertEquals(ExceptionnRole.EVENT_NOT_FOUND.getMessage(), ex.getMessage());
    }
}