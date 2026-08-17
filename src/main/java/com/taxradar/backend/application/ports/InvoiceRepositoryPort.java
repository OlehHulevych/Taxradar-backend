package com.taxradar.backend.application.ports;

import com.taxradar.backend.domain.entities.Invoice;
import com.taxradar.backend.domain.entities.User;
import com.taxradar.backend.domain.enums.InvoiceStatus;

import java.util.List;

public interface InvoiceRepositoryPort {
    List<Invoice> findByIssuer(User issuer);
    List<Invoice> findByStatus(InvoiceStatus status);
    Invoice save(Invoice invoice);
    Invoice findById();
}
