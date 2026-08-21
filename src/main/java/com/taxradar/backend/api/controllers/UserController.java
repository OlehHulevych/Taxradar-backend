package com.taxradar.backend.api.controllers;

import com.taxradar.backend.application.cases.user.commands.UserResponse;
import com.taxradar.backend.application.cases.user.services.UserService;
import com.taxradar.backend.domain.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }



    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(userService.findById(user.getId()));
    }

    @GetMapping
    public ResponseEntity<UserResponse> findByEmail(@RequestParam String email){
        return ResponseEntity.ok(userService.findByEmail(email));
    }
}
