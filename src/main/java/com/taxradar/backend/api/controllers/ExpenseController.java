package com.taxradar.backend.api.controllers;

import com.taxradar.backend.application.cases.Expense.Commands.CreateExpenseRequest;
import com.taxradar.backend.application.cases.Expense.Commands.ExpenseResponse;
import com.taxradar.backend.application.cases.Expense.Services.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ExpenseResponse> createExpense(@RequestBody @Valid CreateExpenseRequest request){
        var response = expenseService.createExpense(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<ExpenseResponse>> getByUser(@PathVariable Long id){
        var response = expenseService.findByUser(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> getByCategory(@RequestParam String category){
        var response = expenseService.findByCategory(category);
        return ResponseEntity.ok(response);
    }

}
