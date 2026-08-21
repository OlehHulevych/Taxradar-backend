package com.taxradar.backend.application.cases.invoice.services;

import com.taxradar.backend.application.cases.invoice.commands.CreateInvoiceRequest;
import com.taxradar.backend.application.cases.invoice.commands.InvoiceResponse;
import com.taxradar.backend.application.ports.InvoiceRepositoryPort;
import com.taxradar.backend.application.ports.UserRepositoryPort;
import com.taxradar.backend.domain.entities.Invoice;
import com.taxradar.backend.domain.enums.Currency;
import com.taxradar.backend.domain.enums.InvoiceStatus;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Transactional
public class InvoiceService {
    private final InvoiceRepositoryPort repository;
    private final UserRepositoryPort userRepository;

    public InvoiceService(InvoiceRepositoryPort repository, UserRepositoryPort userRepository){
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public InvoiceResponse create(CreateInvoiceRequest request){
        var user = userRepository.findById(request.userId()).orElseThrow(()-> new EntityNotFoundException("User not found: "+request.userId()));
        BigDecimal amountWithVat = request.amountWithoutVat().multiply(BigDecimal.ONE.add(request.vatRate().divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)));
        Invoice invoice = new Invoice(request.invoiceNumber(), user, request.clientName(), request.clientIco(),
                request.amountWithoutVat(), request.vatRate(),amountWithVat, Currency.valueOf(request.currency()), request.dueDate(),
                request.issueDate(),null, request.description());
        var saved = repository.save(invoice);
        return toResponse(saved);

    }
    @Transactional(readOnly = true)
    public List<InvoiceResponse> findByIssuer(Long userId){
        var issuer = userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("User not found: "+userId));
        List<Invoice> invoices = repository.findByIssuer(issuer);
        return invoices.stream().map(this::toResponse).toList();

    }

    @Transactional(readOnly = true)
    public List<InvoiceResponse> findByStatus(InvoiceStatus status){
        List<Invoice> invoices = repository.findByStatus(status);
        return invoices.stream().map(this::toResponse).toList();

    }

    private InvoiceResponse toResponse(Invoice invoice){
        return new InvoiceResponse(invoice.getId(),invoice.getInvoiceNumber(),invoice.getStatus().name(),invoice.getIssuer().getId(),
                invoice.getClientName(), invoice.getClientIco(), invoice.getAmountWithoutVat(),
                invoice.getVatRate(), invoice.getAmountWithVat(), invoice.getCurrency().name(),
                invoice.getIssueDate(),invoice.getDueDate(), invoice.getPaidDate(),invoice.getDescription());
    }


}
