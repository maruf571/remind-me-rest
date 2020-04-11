package me.remind.rest.sandbox.validation;

import lombok.RequiredArgsConstructor;
import me.remind.rest.sandbox.exception.UserException;
import me.remind.rest.sandbox.model.User;
import me.remind.rest.sandbox.model.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserValidation {

    private final UserRepository userRepository;

    public void validateUserNotExist(User user) {
        Optional<User> optional =  userRepository.findById(user.getId());
        if(optional.isPresent()) {
            throw new UserException("User is already exist", HttpStatus.BAD_REQUEST);
        }
    }
}
