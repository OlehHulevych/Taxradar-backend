package com.taxradar.backend.application.ports;

import com.taxradar.backend.domain.entities.User;

import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    User save(User user);

}
