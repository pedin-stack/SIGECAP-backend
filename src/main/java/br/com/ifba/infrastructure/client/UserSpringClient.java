package br.com.ifba.infrastructure.client;

import br.com.ifba.infrastructure.dto.UserRequestDTO;
import br.com.ifba.infrastructure.dto.UserResponseDTO;
import br.com.ifba.infrastructure.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserSpringClient {
    private final WebClient webClient;

    public PageWrapper<UserResponseDTO> getAllUsers(int page, int size) {
        log.info("Buscando usuários via WebClient...");

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/users")
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<PageWrapper<UserResponseDTO>>() {})
                .block();
    }

    public UserResponseDTO getUserById(Long id) {
        log.info("Buscando usuário ID {} via WebClient...", id);

        try {
            return webClient.get()
                    .uri("/users/{id}", id)
                    .retrieve()
                    .bodyToMono(UserResponseDTO.class)
                    .block();
        } catch (Exception e) {

            throw new BusinessException("Erro ao buscar usuário no cliente interno: " + e.getMessage());
        }
    }

    public UserResponseDTO saveUser(UserRequestDTO dto) {
        log.info("Salvando novo usuário via WebClient...");

        return webClient.post()
                .uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(dto), UserRequestDTO.class) // Corpo da requisição
                .retrieve()
                .bodyToMono(UserResponseDTO.class) // Resposta esperada
                .block();
    }
}