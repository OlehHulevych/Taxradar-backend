package com.taxradar.backend.application.cases.expense.commands;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public record ExpenseResponse(Long id, Long userId, BigDecimal amount, String currency, LocalDate expenseDate,
                              String description, String category, Boolean isDeductible, BigDecimal deductibleAmount, String receiptUrl, String aiSuggestedCategory, Boolean aiSuggestedDeductible, Instant createdAt, Instant updatedAt) {
}
