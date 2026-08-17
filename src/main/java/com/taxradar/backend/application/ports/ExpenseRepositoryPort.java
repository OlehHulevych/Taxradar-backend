package com.taxradar.backend.application.ports;

import com.taxradar.backend.domain.entities.Expense;
import com.taxradar.backend.domain.entities.User;
import com.taxradar.backend.domain.enums.ExpenseCategory;

import java.util.List;

public interface ExpenseRepositoryPort {
    List<Expense> findByUser(User user);
    List<Expense> findByCategory(ExpenseCategory category);
    Expense save(Expense expense);
}
