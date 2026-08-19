package com.taxradar.backend.application.cases.User.commands;

public record AuthResponse(String token, long expiresIn) {
}
