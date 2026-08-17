package com.taxradar.backend.infrastructure.repositories;

import com.taxradar.backend.application.ports.ExpenseRepositoryPort;
import com.taxradar.backend.domain.entities.Expense;
import com.taxradar.backend.domain.entities.User;
import com.taxradar.backend.domain.enums.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long>, ExpenseRepositoryPort {

}


