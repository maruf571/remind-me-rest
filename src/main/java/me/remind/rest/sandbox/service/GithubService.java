package me.remind.rest.sandbox.service;

import me.remind.rest.sandbox.dto.GithubResponseDto;

import java.util.List;
import java.util.UUID;

public interface GithubService {

    List<GithubResponseDto> findGithubRepositoriesByUserName(UUID id);
}
