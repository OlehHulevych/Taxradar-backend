package com.taxradar.backend.infrastructure.repositories;

import com.taxradar.backend.application.ports.TaxThresholdRepositoryPort;
import com.taxradar.backend.domain.entities.TaxThreshold;
import com.taxradar.backend.domain.enums.ThresholdType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaxThresholdRepository extends JpaRepository<TaxThreshold, Long>, TaxThresholdRepositoryPort {

}
