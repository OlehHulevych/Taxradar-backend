package com.taxradar.backend.domain.entities;

import com.taxradar.backend.domain.common.BaseEntity;
import com.taxradar.backend.domain.enums.Currency;
import com.taxradar.backend.domain.enums.InvoiceStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "invoices")
public class Invoice extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String invoiceNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User issuer;

    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false)
    private String clientIco;
    @Column(nullable = false)
    private BigDecimal amountWithoutVat;

    @Column(nullable = false)
    private BigDecimal vatRate;

    @Column(nullable = false)
    private BigDecimal amountWithVat;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(nullable = false)
    private LocalDate issueDate;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(nullable = true)
    private LocalDate paidDate;

    @Column(nullable = true)
    private String description;

    protected Invoice(){}

    public Invoice(String invoiceNumber, InvoiceStatus status, User issuer, String clientName, String clientIco, BigDecimal amountWithoutVat, BigDecimal vatRate, BigDecimal amountWithVat, Currency currency, LocalDate dueDate, LocalDate issueDate, LocalDate paidDate, String description) {
        this.invoiceNumber = invoiceNumber;
        this.status = status;
        this.issuer = issuer;
        this.clientName = clientName;
        this.clientIco = clientIco;
        this.amountWithoutVat = amountWithoutVat;
        this.vatRate = vatRate;
        this.amountWithVat = amountWithVat;
        this.currency = currency;
        this.dueDate = dueDate;
        this.issueDate = issueDate;
        this.paidDate = paidDate;
        this.description = description;
    }


    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public User getIssuer() {
        return issuer;
    }

    public String getClientName() {
        return clientName;
    }

    public BigDecimal getAmountWithoutVat() {
        return amountWithoutVat;
    }

    public String getClientIco() {
        return clientIco;
    }

    public BigDecimal getVatRate() {
        return vatRate;
    }

    public BigDecimal getAmountWithVat() {
        return amountWithVat;
    }

    public Currency getCurrency() {
        return currency;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public String getDescription() {
        return description;
    }
}
