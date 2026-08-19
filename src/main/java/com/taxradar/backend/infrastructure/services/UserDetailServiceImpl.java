package com.taxradar.backend.infrastructure.services;

import com.taxradar.backend.application.ports.UserRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepositoryPort userRepositoryPort;

    public UserDetailServiceImpl(UserRepositoryPort userRepositoryPort){
        this.userRepositoryPort = userRepositoryPort;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepositoryPort.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found: " + username));
    }


}
