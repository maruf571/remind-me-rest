package me.remind.rest.sandbox.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.remind.rest.sandbox.dto.GithubResponseDto;
import me.remind.rest.sandbox.dto.UserDto;
import me.remind.rest.sandbox.model.User;
import me.remind.rest.sandbox.service.GithubService;
import me.remind.rest.sandbox.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(UserController.URL)
public class UserController {

    public static final String URL = "/users";

    private final UserService userService;

    private final GithubService githubService;

    @GetMapping
    public ResponseEntity<Page<UserDto>> findAllUsers(Pageable pageable, @RequestParam(defaultValue = "") String query) {
        Page<User> page =  userService.findAll(pageable, query);
        return ResponseEntity.ok(
                new PageImpl<>(UserDto.convert(page.getContent()), pageable, page.getTotalElements())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(
                userService.findById(id)
        );
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Validated UserDto dto) {
        log.info("creating new user");
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(UserDto.convert(dto)));
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody @Validated UserDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(
                userService.update(UserDto.convert(dto))
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        userService.delete(id);
    }

    @GetMapping("/{id}/repositories")
    public List<GithubResponseDto> findRepositoriesByUserId(@PathVariable UUID id) {
        return  githubService.findGithubRepositoriesByUserName(id);
    }

}
