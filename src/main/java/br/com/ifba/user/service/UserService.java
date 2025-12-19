package br.com.ifba.user.service;

import br.com.ifba.user.DTO.UserRequestDTO;
import br.com.ifba.user.entity.User;
import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.infrastructure.role.ExceptionnRole;
import br.com.ifba.user.repository.UserRepository;
import br.com.ifba.usertype.entity.UserType;
import br.com.ifba.usertype.repository.UserTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;

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
    public User update(Long id, UserRequestDTO dto) {
        // 1. RECUPERAR (Busca o usuário persistido no banco)
        // Isso coloca o objeto no estado "MANAGED" do Hibernate
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(ExceptionnRole.USER_NOT_FOUND.getMessage()));

        user.setName(dto.getName());
        user.setCpf(dto.getCpf());
        user.setPhone(dto.getPhone());

        // 3. ATUALIZAR DADOS DE USER (Campos próprios)
        user.setEmail(dto.getEmail());
        user.setActive(dto.isActive());

        // Se a senha foi enviada, atualiza (idealmente criptografada)
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(dto.getPassword());
        }

        if (dto.getUserType().getDescription() != null) {
            String enumName = dto.getUserType().getDescription().name(); // "DEMOLAY"
            // buscar UserType por alguma propriedade (ex.: typeName igual a label ou name)
            UserType userType = (UserType) userTypeRepository.findByDescription(enumName); // implemente/ajuste esse método
            user.setUserType(userType);
        }

        return userRepository.save(user);
    }

    @Transactional
    public void delete(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(ExceptionnRole.USER_NOT_FOUND.getMessage()));

        user.setActive(false);

        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(ExceptionnRole.USER_NOT_FOUND_EMAIL.getMessage()));
    }

    public java.util.List<User> findByStatus(boolean status) {
        return userRepository.findByActive(status);
    }

    public java.util.List<User> findByUserType(Long typeId) {
        return userRepository.findByUserTypeId(typeId);
    }
}