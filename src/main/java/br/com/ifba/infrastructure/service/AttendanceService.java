package br.com.ifba.infrastructure.service;

import br.com.ifba.infrastructure.entity.Attendance;
import br.com.ifba.infrastructure.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    // Listar todos
    public List<Attendance> findAll() {
        return attendanceRepository.findAll();
    }

    // Buscar por ID
    public Attendance findById(Long id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance não encontrado com ID: " + id));
    }

    // Salvar
    @Transactional
    public Attendance save(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    // Deletar
    @Transactional
    public void delete(Long id) {
        if (!attendanceRepository.existsById(id)) {
            throw new RuntimeException("Attendance não encontrado para exclusão.");
        }
        attendanceRepository.deleteById(id);
    }
}