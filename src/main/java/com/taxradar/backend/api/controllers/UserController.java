package com.taxradar.backend.api.controllers;

import com.taxradar.backend.application.cases.User.commands.CreateUserRequest;
import com.taxradar.backend.application.cases.User.commands.UserResponse;
import com.taxradar.backend.application.cases.User.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid CreateUserRequest request){
        UserResponse response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping
    public ResponseEntity<UserResponse> findByEmail(@RequestParam String email){
        return ResponseEntity.ok(userService.findByEmail(email));
    }
}
