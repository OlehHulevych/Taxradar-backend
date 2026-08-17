package com.taxradar.backend.application.cases.Invoice.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateInvoiceRequest(@NotNull Long userId,
                                   @NotBlank String invoiceNumber,
                                   @NotBlank String clientName, @NotBlank String clientIco, @NotNull @Positive
                                   BigDecimal amountWithoutVat, @NotNull @Positive BigDecimal vatRate, @NotBlank String currency,
                                   @NotNull LocalDate issueDate, @NotNull LocalDate dueDate, String description ) {
}
