package br.com.ifba.infrastructure.service;

import br.com.ifba.infrastructure.entity.UserType;
import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.infrastructure.repository.UserTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserTypeService {

    private final UserTypeRepository userTypeRepository;

    public Page<UserType> findAll(Pageable pageable) {
        return userTypeRepository.findAll(pageable);
    }

    public UserType findById(Long id) {
        return userTypeRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Tipo de Usuário não encontrado com ID: " + id));
    }

    @Transactional
    public UserType save(UserType userType) {
        return userTypeRepository.save(userType);
    }

    @Transactional
    public void delete(Long id) {
        if (!userTypeRepository.existsById(id)) {
            throw new BusinessException("Tipo de Usuário não encontrado para exclusão.");
        }
        userTypeRepository.deleteById(id);
    }

    public List<UserType> findByTypeName(String typeName) {
        return userTypeRepository.findByTypeName(typeName);
    }

    public List<UserType> findByDescription(String description) {
        return userTypeRepository.findByDescription(description);
    }
}