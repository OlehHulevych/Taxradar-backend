package com.taxradar.backend.application.cases.user.commands;

import jakarta.validation.constraints.NotBlank;

public record LoginCommand(@NotBlank String email, @NotBlank String password) {
}
