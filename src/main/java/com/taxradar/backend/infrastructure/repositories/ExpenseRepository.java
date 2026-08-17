package com.taxradar.backend.infrastructure.repositories;

import com.taxradar.backend.application.ports.ExpenseRepositoryPort;
import com.taxradar.backend.domain.entities.Expense;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ExpenseRepository extends JpaRepository<Expense, Long>, ExpenseRepositoryPort {

}


