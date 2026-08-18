package com.taxradar.backend.application.ports;

import com.taxradar.backend.domain.entities.TaxThreshold;
import com.taxradar.backend.domain.enums.ThresholdType;

import java.util.List;
import java.util.Optional;

public interface TaxThresholdRepositoryPort {
    Optional<TaxThreshold> findByTaxYearAndType(int taxYear, ThresholdType type);
    List<TaxThreshold> findByTaxYear(int taxYear);
    TaxThreshold save(TaxThreshold taxThreshold);
}
