package br.com.ifba.infrastructure.dto;

import br.com.ifba.infrastructure.role.StatusRole;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EventRequestDTO {
    private String name;
    private LocalDateTime date;
    private String local;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private StatusRole statusRole;
    private String eventPics;
}