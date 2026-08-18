package com.taxradar.backend.infrastructure.repositories;

import com.taxradar.backend.application.ports.InvoiceRepositoryPort;
import com.taxradar.backend.domain.entities.Invoice;

import org.springframework.data.jpa.repository.JpaRepository;



public interface InvoiceRepository extends JpaRepository<Invoice,Long>, InvoiceRepositoryPort {

}
