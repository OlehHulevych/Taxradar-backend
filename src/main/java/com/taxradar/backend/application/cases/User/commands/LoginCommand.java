package com.taxradar.backend.application.cases.User.commands;

import jakarta.validation.constraints.NotBlank;

public record LoginCommand(@NotBlank String email, @NotBlank String password) {
}
