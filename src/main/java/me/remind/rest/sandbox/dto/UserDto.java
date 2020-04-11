package me.remind.rest.sandbox.dto;

import lombok.Data;
import me.remind.rest.sandbox.model.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class UserDto {

    private UUID id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String surname;

    @NotBlank
    private String position;

    @NotBlank
    private String gitHubProfileUrl;

    public static User convert(UserDto dto) {
        User user = new User();
        if(dto.getId() != null) {
            user.setId(dto.id);
        }
        user.setFirstName(dto.firstName);
        user.setSurname(dto.surname);
        user.setPosition(dto.position);
        user.setGitHubProfileUrl(dto.gitHubProfileUrl);
        return user;
    }

    public static UserDto convert(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setSurname(user.getSurname());
        dto.setPosition(user.getPosition());
        dto.setGitHubProfileUrl(user.getGitHubProfileUrl());
        return dto;
    }

    public static List<UserDto> convert(List<User> users) {
        return users.stream().map(UserDto::convert).collect(Collectors.toList());
    }

}
