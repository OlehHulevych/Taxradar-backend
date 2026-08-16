package com.taxradar.backend.application.cases.User.services;

import com.taxradar.backend.application.cases.User.commands.CreateUserRequest;
import com.taxradar.backend.application.cases.User.commands.UserResponse;
import com.taxradar.backend.application.ports.UserRepositoryPort;
import com.taxradar.backend.domain.entities.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    private final UserRepositoryPort userRepository;

    public UserService(UserRepositoryPort userRepository){
        this.userRepository = userRepository;
    }

    public UserResponse createUser(CreateUserRequest request){
        User user = new User(
                request.firstName(),
                request.lastName(),
                request.phone(),
                request.email(),
                request.birthdate(),
                request.ico()
        );
        User saved = userRepository.save(user);
        return toResponse(saved);
    }



    @Transactional(readOnly = true)
    public UserResponse findById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("User not found: "+id));
        return toResponse(user);

    }

    @Transactional(readOnly = true)
    public UserResponse findByEmail(String email){
        User user =userRepository.findByEmail(email)
                .orElseThrow(()->new EntityNotFoundException("User is not found by email: "+email));
        return toResponse(user);
    }

    private UserResponse toResponse(User user){
        return new UserResponse(user.getId(), user.getFirstName(), user.getLastName(),user.getEmail(),user.getPhone(), user.getBirthdate(), user.getIco());
    }


}
