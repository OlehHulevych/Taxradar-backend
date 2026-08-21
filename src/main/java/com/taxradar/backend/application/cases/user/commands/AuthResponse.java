package com.taxradar.backend.application.cases.user.commands;

public record AuthResponse(String token, long expiresIn) {
}
