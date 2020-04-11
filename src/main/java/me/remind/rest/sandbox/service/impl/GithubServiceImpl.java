package me.remind.rest.sandbox.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.remind.rest.sandbox.dto.GithubResponseDto;
import me.remind.rest.sandbox.exception.UserException;
import me.remind.rest.sandbox.model.User;
import me.remind.rest.sandbox.model.repository.UserRepository;
import me.remind.rest.sandbox.service.GithubService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class GithubServiceImpl implements GithubService {

    @Value("${github.baseUrl}")
    private String githubBaseUrl;

    @Value("${github.token}")
    private String githubToken;

    private static final String USER_REPO = "users/%s/repos";

    private final RestTemplate restTemplate;

    private static HttpEntity<String> entity ;

    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "token " + githubToken);
        entity = new HttpEntity<>(headers);
    }

    @Override
    public List<GithubResponseDto> findGithubRepositoriesByUserId(UUID id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserException("User not found", HttpStatus.NOT_FOUND)
        );

        ResponseEntity<List<GithubResponseDto>> response = restTemplate.exchange(
                githubBaseUrl + String.format(USER_REPO, user.getGitHubProfileUrl()),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<GithubResponseDto>>(){}
        );
        if(response.getStatusCode().isError()) {
            throw new UserException("Github server response error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response.getBody();
    }
}
