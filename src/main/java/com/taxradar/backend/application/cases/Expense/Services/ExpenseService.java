package com.taxradar.backend.application.cases.Expense.Services;

import com.taxradar.backend.application.cases.Expense.Commands.CreateExpenseRequest;
import com.taxradar.backend.application.cases.Expense.Commands.ExpenseResponse;
import com.taxradar.backend.application.ports.ExpenseRepositoryPort;
import com.taxradar.backend.application.ports.UserRepositoryPort;
import com.taxradar.backend.domain.entities.Expense;
import com.taxradar.backend.domain.enums.Currency;
import com.taxradar.backend.domain.enums.ExpenseCategory;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExpenseService {

    private final ExpenseRepositoryPort repository;
    private final UserRepositoryPort userRepository;

    public ExpenseService(ExpenseRepositoryPort repository, UserRepositoryPort userRepository){
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public ExpenseResponse createExpense(CreateExpenseRequest request){
        var user = userRepository.findById(request.userId()).orElseThrow(()->new EntityNotFoundException("User is not found: "+request.userId()));
        var expense = new Expense(user, request.amount(),request.expenseDate(), Currency.valueOf(request.currency()), request.description(),
                ExpenseCategory.valueOf(request.category()),request.isDeductible());
        var saved = repository.save(expense);
        return toResponse(saved);

    }

    @Transactional(readOnly = true)
    public List<ExpenseResponse> findByUser(Long userId){
        var user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User is not found: "+userId));
        List<Expense> expenseList = repository.findByUser(user);
        return expenseList.stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<ExpenseResponse> findByCategory(String category){
        List<Expense> expenseList = repository.findByCategory(ExpenseCategory.valueOf(category));
        return expenseList.stream().map(this::toResponse).toList();
    }

    private ExpenseResponse toResponse(Expense expense){
        return new ExpenseResponse(expense.getId(),expense.getUser().getId(),expense.getAmount(),expense.getCurrency().name(),expense.getExpenseDate(),expense.getDescription(),expense.getCategory().name(),expense.isDeductible(),expense.getDeductibleAmount(),
                expense.getReceiptUrl(),expense.getAiSuggestedCategory(),expense.getAiSuggestedDeductible(),expense.getCreatedAt(),expense.getUpdatedAt());
    }


}
