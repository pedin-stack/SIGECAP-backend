package br.com.ifba.infrastructure.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import br.com.ifba.infrastructure.entity.Attendance;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/attendances")
@CrossOrigin(origins = "http://localhost:3000")
public class AttendanceController {

    private final List<Attendance> attendances = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Attendance>> getAttendanceByEvent(@PathVariable Long eventId) {
        List<Attendance> eventAttendances = attendances.stream()
                .filter(a -> a.getEvent() != null && a.getEvent().getId().equals(eventId))
                .collect(Collectors.toList());
        return ResponseEntity.ok(eventAttendances);
    }

    @PostMapping
    public ResponseEntity<Attendance> registerAttendance(@RequestBody Attendance attendance) {
        attendance.setId(idCounter.getAndIncrement());
        attendances.add(attendance);
        return ResponseEntity.status(201).body(attendance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attendance> updateAttendance(@PathVariable Long id, @RequestBody Attendance attendanceUpdated) {
        Optional<Attendance> attOpt = attendances.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();

        if (attOpt.isPresent()) {
            Attendance existing = attOpt.get();
            //existing.setStatus(attendanceUpdated.getStatus());
            existing.setJustification(attendanceUpdated.getJustification());
            return ResponseEntity.ok(existing);
        }
        return ResponseEntity.notFound().build();
    }
}