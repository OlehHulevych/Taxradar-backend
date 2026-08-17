package com.taxradar.backend.infrastructure.repositories;

import com.taxradar.backend.application.ports.InvoiceRepositoryPort;
import com.taxradar.backend.application.ports.TaxThresholdRepositoryPort;
import com.taxradar.backend.domain.entities.Invoice;
import com.taxradar.backend.domain.entities.User;
import com.taxradar.backend.domain.enums.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice,Long>, InvoiceRepositoryPort {

}
