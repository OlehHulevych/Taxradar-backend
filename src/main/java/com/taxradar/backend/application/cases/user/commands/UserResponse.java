package com.taxradar.backend.application.cases.user.commands;

import java.time.LocalDate;

public record UserResponse(Long id, String firstName, String lastName, String email, String phone, LocalDate birthdate, String ico) {
}
