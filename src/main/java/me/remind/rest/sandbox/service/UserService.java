package me.remind.rest.sandbox.service;

import me.remind.rest.sandbox.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {

    Page<User> findAll(Pageable pageable, String query);

    User findById(UUID id);

    User create(User user);

    User update(User user);

    void delete(UUID id);

}
