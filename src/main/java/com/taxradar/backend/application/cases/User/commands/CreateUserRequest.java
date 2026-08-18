package com.taxradar.backend.application.cases.User.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateUserRequest(@NotBlank  String firstName, @NotBlank String lastName, @NotBlank String phone, @NotBlank String email, @NotNull LocalDate birthdate, @NotBlank String ico) {

}
