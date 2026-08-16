package com.taxradar.backend.infrastructure.repositories;

import com.taxradar.backend.domain.entities.Invoice;
import com.taxradar.backend.domain.entities.User;
import com.taxradar.backend.domain.enums.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    List<Invoice> findByIssuer(User issuer);
    List<Invoice> findByStatus(InvoiceStatus status);
}
