package com.taxradar.backend.application.cases.taxthreshold.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateTaxThresholdRequest(@NotNull Integer taxYear, @NotBlank String type, @NotNull @Positive BigDecimal annualIncomeLimit, @Positive BigDecimal monthlyPayment, @NotNull
                                        LocalDate validFrom, LocalDate validTo) {
}
