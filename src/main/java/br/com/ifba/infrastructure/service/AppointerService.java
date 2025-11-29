package br.com.ifba.infrastructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ifba.infrastructure.entity.Appointer;
import br.com.ifba.infrastructure.repository.AppointerRepository;

import java.util.List;

@Service
public class AppointerService {

    @Autowired
    private AppointerRepository appointerRepository;

    public List<Appointer> findAll() {
        return appointerRepository.findAll();
    }

    // RF02 - Propor nova nominata
    public Appointer save(Appointer appointer) {
        // Regra: Validar datas da gestão
        if (appointer.getStartDate().isAfter(appointer.getEndDate())) {
            throw new RuntimeException("Data de início inválida.");
        }

       // appointer.setStatus(AppointerStatus.PROPOSTA); // Status inicial
        return appointerRepository.save(appointer);
    }

    // RF02 - Validação de nominatas pelo Conselho
    public Appointer updateStatus(Long id, String newStatus) {
        Appointer appointer = appointerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nominata não encontrada"));

        try {
            //ppointerStatus status = AppointerStatus.valueOf(newStatus);
//appointer.setStatus(status);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Status inválido");
        }

        return appointerRepository.save(appointer);
    }
}