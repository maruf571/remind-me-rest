package me.remind.rest.sandbox.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GithubResponseDto {

    private Integer id;

    private String name;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("html_url")
    private String htmlUrl;

    private String description;

    private String url;

    @JsonProperty("forks_count")
    private Integer forksCount;

    @JsonProperty("watchers_count")
    private Integer watchersCount;

    @JsonProperty("created_at")
    private LocalDate createdAt;

    private String language;
}
