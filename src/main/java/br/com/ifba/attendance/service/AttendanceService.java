package br.com.ifba.attendance.service;

import br.com.ifba.attendance.entity.Attendance;
import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.attendance.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    // Listar todos
    public Page<Attendance> findAll(Pageable pageable) {
        return attendanceRepository.findAll(pageable);
    }

    // Buscar por ID
    public Attendance findById(Long id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Attendance não encontrado com ID: " + id));
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
            throw new BusinessException("Attendance não encontrado para exclusão.");
        }
        attendanceRepository.deleteById(id);
    }
}