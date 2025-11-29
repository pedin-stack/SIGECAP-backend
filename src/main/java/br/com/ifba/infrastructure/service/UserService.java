package br.com.ifba.infrastructure.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.ifba.infrastructure.entity.User;
import br.com.ifba.infrastructure.repository.UserRepository;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public User save(User user) {
        // Regra: Validar se o e-mail já existe
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado.");
        }

        //user.setIsActive(true);

        return userRepository.save(user);
    }

    public User update(Long id, User userUpdated) {
        User existingUser = findById(id);

        // Atualiza apenas campos permitidos
        existingUser.setEmail(userUpdated.getEmail());
       // existingUser.setProfile(userUpdated.getProfile());
        existingUser.setName(userUpdated.getName());
        existingUser.setAddress(userUpdated.getAddress());

        return userRepository.save(existingUser);
    }

    public void delete(Long id) {
        // Regra: Soft delete (desativação) é preferível a apagar do banco para manter histórico
        User user = findById(id);
        //user.setIsActive(false);
        userRepository.save(user);
    }
}
