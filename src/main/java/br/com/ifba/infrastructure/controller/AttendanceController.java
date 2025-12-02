package br.com.ifba.infrastructure.controller;

import br.com.ifba.infrastructure.entity.Attendance;
import br.com.ifba.infrastructure.service.AttendanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendances")
@CrossOrigin(origins = "http://localhost:3000")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    //Listar Todos
    @GetMapping
    public ResponseEntity<List<Attendance>> findAll() {
        return ResponseEntity.ok(attendanceService.findAll());
    }

    //Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Attendance> findById(@PathVariable Long id) {
        return ResponseEntity.ok(attendanceService.findById(id));
    }

    //Criar
    @PostMapping
    public ResponseEntity<Attendance> save(@RequestBody Attendance attendance) {
        Attendance savedAttendance = attendanceService.save(attendance);
        // Retorna 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAttendance);
    }

    //Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Attendance> update(@PathVariable Long id, @RequestBody Attendance attendance) {
        attendance.setId(id);
        Attendance updatedAttendance = attendanceService.save(attendance);
        // Retorna 200 OK
        return ResponseEntity.ok(updatedAttendance);
    }

    //Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        attendanceService.delete(id);
        // Retorna 204 No Content
        return ResponseEntity.noContent().build();
    }
}