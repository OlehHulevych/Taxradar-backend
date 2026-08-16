package com.taxradar.backend.domain.entities;

import com.taxradar.backend.domain.common.BaseEntity;
import com.taxradar.backend.domain.enums.Currency;
import com.taxradar.backend.domain.enums.ExpenseCategory;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="expenses")
public class Expense extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(nullable = false)
    private LocalDate expenseDate;
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ExpenseCategory category;

    @Column(nullable = false)
    private boolean isDeductible;

    @Column(nullable = true)
    private BigDecimal deductibleAmount;

    @Column(nullable = true)
    private String receiptUrl;

    @Column(nullable = true)
    private String aiSuggestedCategory;

    @Column(nullable = true)
    private Boolean aiSuggestedDeductible;


    protected Expense(){

    }

    public Expense(User user, BigDecimal amount, LocalDate expenseDate, Currency currency, String description, ExpenseCategory category, boolean isDeductible){
        this.user = user;
        this.amount = amount;
        this.expenseDate = expenseDate;
        this.currency = currency;
        this.description = description;
        this.category = category;
        this.isDeductible = isDeductible;
    }

    public User getUser() {
        return user;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDeductible() {
        return isDeductible;
    }

    public BigDecimal getDeductibleAmount() {
        return deductibleAmount;
    }

    public String getReceiptUrl() {
        return receiptUrl;
    }

    public String getAiSuggestedCategory() {
        return aiSuggestedCategory;
    }

    public Boolean getAiSuggestedDeductible() {
        return aiSuggestedDeductible;
    }

    public void setAiSuggestedDeductible(Boolean aiSuggestedDeductible) {
        this.aiSuggestedDeductible = aiSuggestedDeductible;
    }

    public void setAiSuggestedCategory(String aiSuggestedCategory) {
        this.aiSuggestedCategory = aiSuggestedCategory;
    }

    public void setReceiptUrl(String receiptUrl) {
        this.receiptUrl = receiptUrl;
    }

    public void setDeductibleAmount(BigDecimal deductibleAmount) {
        this.deductibleAmount = deductibleAmount;
    }
}
