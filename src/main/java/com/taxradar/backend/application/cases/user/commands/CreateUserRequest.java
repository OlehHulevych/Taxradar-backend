package com.taxradar.backend.application.cases.user.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateUserRequest(@NotBlank  String firstName, @NotBlank String lastName, @NotBlank String phone, @NotBlank String email, @NotNull LocalDate birthdate, @NotBlank @Size(min = 8) String password, @NotBlank String ico) {

}
