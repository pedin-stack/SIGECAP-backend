package br.com.ifba.infrastructure.service;

import br.com.ifba.infrastructure.entity.Appointer;
import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.infrastructure.role.DeMolayRole;
import br.com.ifba.infrastructure.role.StatusRole; // Assumindo que este é o enum do seu campo statusRole
import br.com.ifba.infrastructure.repository.AppointerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointerService {

    private final AppointerRepository appointerRepository;

    public AppointerService(AppointerRepository appointerRepository) {
        this.appointerRepository = appointerRepository;
    }


    public List<Appointer> findAll() {
        return appointerRepository.findAll();
    }

    public Appointer findById(Long id) {
        return appointerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Nominata não encontrada com ID: " + id));
    }

    @Transactional
    public Appointer save(Appointer appointer) {
        // Data de fim não pode ser antes do início
        if (appointer.getStartDate().isAfter(appointer.getEndDate())) {
            throw new BusinessException("A data de início não pode ser posterior à data de término.");
        }

        if (appointer.getMembers() != null && !appointer.getMembers().isEmpty()) {
            appointer.getMembers().forEach(member -> member.setAppointer(appointer));
        }

        return appointerRepository.save(appointer);
    }

    @Transactional
    public void delete(Long id) {
        if (!appointerRepository.existsById(id)) {
            throw new BusinessException("Nominata não encontrada para exclusão.");
        }
        appointerRepository.deleteById(id);
    }

    // =================================================================================
    // 2. MÉTODOS DE BUSCA ESPECÍFICOS
    // =================================================================================

    public List<Appointer> findByStartDate(LocalDate date) {
        return appointerRepository.findByStartDate(date);
    }

    public List<Appointer> findByEndDate(LocalDate date) {
        return appointerRepository.findByEndDate(date);
    }
    //id do usuario que quero + cargo para identifcar se ele ja ocupou tal
    public List<Appointer> findByMemberAndRole(Long userId, DeMolayRole role) {
        return appointerRepository.findByMemberAndRole(userId, role);
    }

    // =================================================================================
    // 3. ATUALIZAÇÃO DE STATUS
    // =================================================================================

    @Transactional
    public Appointer updateStatus(Long id, StatusRole newStatus) {
        Appointer appointer = findById(id);
        appointer.setStatusRole(newStatus);
        return appointerRepository.save(appointer);
    }
}