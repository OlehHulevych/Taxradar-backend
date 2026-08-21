package com.taxradar.backend.application.cases.invoice.commands;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InvoiceResponse(Long id, String invoiceNumber, String status, Long userId, String clientName, String clientIco,
                              BigDecimal amountWithoutVat, BigDecimal vatRate,
                              BigDecimal amountWithVat, String currency,
                              LocalDate issueDate, LocalDate dueDate, LocalDate paidDate,String description) {
}
