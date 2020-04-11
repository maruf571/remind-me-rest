package me.remind.rest.sandbox.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.remind.rest.sandbox.exception.UserException;
import me.remind.rest.sandbox.model.User;
import me.remind.rest.sandbox.model.repository.UserRepository;
import me.remind.rest.sandbox.service.UserService;
import me.remind.rest.sandbox.validation.UserValidation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserValidation userValidation;

    @Override
    public Page<User> findAll(Pageable pageable, String query) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserException("User not found", HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public User create(User user) {
        // Validate user
        userValidation.validateUserNotExist(user);

        // Save user
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        // TODO:: Validate user input those are not possible with annotation

        User existingUser = findById(user.getId());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setSurname(user.getSurname());
        existingUser.setGitHubProfileUrl(user.getGitHubProfileUrl());
        existingUser.setPosition(user.getPosition());
        return userRepository.save(existingUser);
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

}
