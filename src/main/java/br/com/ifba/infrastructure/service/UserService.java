package br.com.ifba.infrastructure.service;

import br.com.ifba.infrastructure.entity.User;
import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.infrastructure.role.ExceptionnRole;
import br.com.ifba.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ExceptionnRole.USER_NOT_FOUND.getMessage()));
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void delete(Long id) {
        User user = findById(id);
        user.setIsactive(false);
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(ExceptionnRole.USER_NOT_FOUND_EMAIL.getMessage()));
    }

    public java.util.List<User> findByStatus(boolean status) {
        return userRepository.findByIsactive(status);
    }

    public java.util.List<User> findByUserType(Long typeId) {
        return userRepository.findByUserTypeId(typeId);
    }
}