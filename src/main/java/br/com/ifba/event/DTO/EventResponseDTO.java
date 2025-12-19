package br.com.ifba.event.DTO;

import br.com.ifba.infrastructure.role.EventRole;
import br.com.ifba.infrastructure.role.StatusRole;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EventResponseDTO {
    private Long id;
    private String name;
    private LocalDateTime date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private EventRole eventRole ;
}