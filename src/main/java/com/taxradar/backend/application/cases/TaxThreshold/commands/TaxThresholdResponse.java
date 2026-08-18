package com.taxradar.backend.application.cases.TaxThreshold.commands;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public record TaxThresholdResponse(Long id, int taxYear, String type, BigDecimal annualIncomeLimit, BigDecimal monthlyPayment,
                                   LocalDate validFrom, LocalDate validTo, Instant createdAt, Instant updatedAt) {
}
