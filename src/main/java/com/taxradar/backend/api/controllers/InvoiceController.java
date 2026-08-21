package com.taxradar.backend.api.controllers;
import com.taxradar.backend.application.cases.invoice.commands.CreateInvoiceRequest;
import com.taxradar.backend.application.cases.invoice.commands.InvoiceResponse;
import com.taxradar.backend.application.cases.invoice.services.InvoiceService;
import com.taxradar.backend.domain.entities.User;
import com.taxradar.backend.domain.enums.InvoiceStatus;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;
    public InvoiceController(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public ResponseEntity<InvoiceResponse> createInvoice(@RequestBody @Valid CreateInvoiceRequest request,
      @AuthenticationPrincipal User user){
        var response = invoiceService.create(request, user.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    public ResponseEntity<List<InvoiceResponse>> findByIssuer( @AuthenticationPrincipal User user){
        var response = invoiceService.findByIssuer(user.getId());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/status")
    public ResponseEntity<List<InvoiceResponse>> getByStatus(@RequestParam String status){
        var response = invoiceService.findByStatus(InvoiceStatus.valueOf(status));
        return ResponseEntity.ok(response);
    }
}
