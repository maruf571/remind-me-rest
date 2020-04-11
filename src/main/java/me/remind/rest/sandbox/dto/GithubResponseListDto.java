package me.remind.rest.sandbox.dto;

import lombok.Data;

import java.util.List;

@Data
public class GithubResponseListDto {

    List<GithubResponseDto> githubResponseDtoList;
}
