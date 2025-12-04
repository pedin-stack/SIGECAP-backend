package br.com.ifba.infrastructure.service;

import br.com.ifba.infrastructure.entity.User;
import br.com.ifba.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import br.com.ifba.infrastructure.exception.BusinessException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado com ID: " + id));
    }

    @Transactional
    public User save(User user) {

        if (user.getId() == null) {
            user.setIsactive(true);
        }
        return userRepository.save(user);
    }


    @Transactional
    public void delete(Long id) {
       //SOFT DELETE
        User user = findById(id); // O findById já lança BusinessException se não achar
        user.setIsactive(false);
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado com o email: " + email));
    }

    public List<User> findByStatus(boolean status) {
        return userRepository.findByIsactive(status);
    }

    public List<User> findByUserType(Long typeId) {
        return userRepository.findByUserTypeId(typeId);
    }
}