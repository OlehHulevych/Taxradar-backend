package com.taxradar.backend.application.cases.User.commands;

import java.time.LocalDate;

public record CreateUserRequest(String firstName, String lastName, String phone, String email, LocalDate birthdate, String ico) {

}
