package com.taxradar.backend.domain.entities;

import com.taxradar.backend.domain.common.BaseEntity;
import com.taxradar.backend.domain.enums.ThresholdType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="tax_thresholds", uniqueConstraints  ={@UniqueConstraint(columnNames = {"tax_year", "type"})})
public class TaxThreshold extends BaseEntity {

    @Column(nullable = false)
    private int taxYear;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ThresholdType type;

    @Column(nullable = false)
    private BigDecimal annualIncomeLimit;

    @Column(nullable = true)
    private BigDecimal monthlyPayment;

    @Column(nullable = false)
    private LocalDate validFrom;

    @Column(nullable = true)
    private LocalDate validTo;

    protected TaxThreshold(){

    }

    public TaxThreshold(int taxYear, ThresholdType type, BigDecimal annualIncomeLimit, BigDecimal monthlyPayment, LocalDate validFrom, LocalDate validTo) {
        this.taxYear = taxYear;
        this.type = type;
        this.annualIncomeLimit = annualIncomeLimit;
        this.monthlyPayment = monthlyPayment;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public int getTaxYear() {
        return taxYear;
    }

    public ThresholdType getType() {
        return type;
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    public BigDecimal getAnnualIncomeLimit() {
        return annualIncomeLimit;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }
}
