package com.taxradar.backend.api.controllers;

import com.taxradar.backend.application.cases.User.commands.AuthResponse;
import com.taxradar.backend.application.cases.User.commands.CreateUserRequest;
import com.taxradar.backend.application.cases.User.commands.LoginCommand;
import com.taxradar.backend.application.cases.User.commands.UserResponse;
import com.taxradar.backend.application.cases.User.services.UserService;
import com.taxradar.backend.infrastructure.services.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthController(JwtService jwtService, AuthenticationManager authenticationManager, UserService userService){
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginCommand loginCommand){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginCommand.email(),loginCommand.password()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        var token = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token,jwtService.getExpirationTime()));
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid CreateUserRequest request){
        UserResponse response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
