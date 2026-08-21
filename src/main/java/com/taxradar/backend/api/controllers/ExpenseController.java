package com.taxradar.backend.api.controllers;

import com.taxradar.backend.application.cases.expense.commands.CreateExpenseRequest;
import com.taxradar.backend.application.cases.expense.commands.ExpenseResponse;
import com.taxradar.backend.application.cases.expense.services.ExpenseService;
import com.taxradar.backend.domain.entities.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(@RequestBody @Valid CreateExpenseRequest request, @AuthenticationPrincipal User user){
        var response = expenseService.createExpense(request, user.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/user")
    public ResponseEntity<List<ExpenseResponse>> getByUser(@AuthenticationPrincipal User user){
        var response = expenseService.findByUser(user.getId());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/category")
    public ResponseEntity<List<ExpenseResponse>> getByCategory(@RequestParam String category){
        var response = expenseService.findByCategory(category);
        return ResponseEntity.ok(response);
    }

}
