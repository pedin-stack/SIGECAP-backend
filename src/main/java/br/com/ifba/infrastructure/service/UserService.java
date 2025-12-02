package br.com.ifba.infrastructure.service;

import br.com.ifba.infrastructure.entity.User;
import br.com.ifba.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
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

        User user = findById(id);


        user.setIsactive(false);

        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o email: " + email));
    }

    public List<User> findByStatus(boolean status) {
        return userRepository.findByIsactive(status);
    }

    public List<User> findByUserType(Long typeId) {
        return userRepository.findByUserTypeId(typeId);
    }
}