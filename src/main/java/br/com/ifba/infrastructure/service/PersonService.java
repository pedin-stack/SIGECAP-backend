package br.com.ifba.infrastructure.service;

import br.com.ifba.infrastructure.entity.Person;
import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.infrastructure.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // Injeção de dependência automática (Lombok)
public class PersonService {

    private final PersonRepository personRepository;

    // Listar todos
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    // Buscar por ID
    public Person findById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Pessoa não encontrada com ID: " + id));
    }

    // Salvar (Criar ou Atualizar)
    @Transactional
    public Person save(Person person) {
        return personRepository.save(person);
    }

    // Deletar
    @Transactional
    public void delete(Long id) {
        if (!personRepository.existsById(id)) {
            throw new BusinessException("Pessoa não encontrada para exclusão.");
        }
        personRepository.deleteById(id);
    }
}