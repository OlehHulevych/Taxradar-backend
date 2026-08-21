package com.taxradar.backend.application.cases.expense.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateExpenseRequest(@NotNull Long userId, @NotNull @Positive BigDecimal amount, @NotBlank String currency, @NotNull
                                   LocalDate expenseDate, @NotBlank String description, @NotBlank String category, @NotNull Boolean isDeductible) {
}
