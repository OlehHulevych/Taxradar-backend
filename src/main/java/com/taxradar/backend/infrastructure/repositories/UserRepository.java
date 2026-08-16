package com.taxradar.backend.infrastructure.repositories;

import com.taxradar.backend.application.ports.UserRepositoryPort;
import com.taxradar.backend.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryPort {

}
