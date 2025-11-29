package br.com.ifba.infrastructure.service;
import br.com.ifba.infrastructure.entity.Attendance;
import br.com.ifba.infrastructure.repository.AttendanceRepository;
import br.com.ifba.infrastructure.repository.EventRepository;
import br.com.ifba.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;

    public List<Attendance> findByEvent(Long eventId) {
        return attendanceRepository.findByEventId(eventId);
    }

    public Attendance save(Attendance attendance) {
        // Verifica se usuário e evento existem
        userRepository.findById(attendance.getMember().getId())
                .orElseThrow(() -> new RuntimeException("Membro inválido"));
        eventRepository.findById(attendance.getEvent().getId())
                .orElseThrow(() -> new RuntimeException("Evento inválido"));

        return attendanceRepository.save(attendance);
    }

    // escrivão deve computar,em caso negativo poderá ser posta justificativa
    public Attendance update(Long id, Attendance attendanceUpdated) {
        Attendance existing = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de presença não encontrado"));

       // existing.setStatus(attendanceUpdated.getStatus()); // PRESENTE ou AUSENTE

    /*    if (AttendanceStatus.AUSENTE.equals(existing.getStatus()) &&
                (attendanceUpdated.getJustification() != null && !attendanceUpdated.getJustification().isEmpty())) {
            existing.setJustification(attendanceUpdated.getJustification());
        } */

        return attendanceRepository.save(existing);
    }
}