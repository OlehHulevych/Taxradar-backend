package com.taxradar.backend.application.cases.TaxThreshold.services;

import com.taxradar.backend.application.cases.TaxThreshold.commands.CreateTaxThresholdRequest;
import com.taxradar.backend.application.cases.TaxThreshold.commands.TaxThresholdResponse;
import com.taxradar.backend.application.ports.TaxThresholdRepositoryPort;
import com.taxradar.backend.domain.entities.TaxThreshold;
import com.taxradar.backend.domain.enums.ThresholdType;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
@Transactional
public class TaxThresholdService {
    private final TaxThresholdRepositoryPort repository;

    public TaxThresholdService(TaxThresholdRepositoryPort repository){
        this.repository = repository;
    }

    public TaxThresholdResponse save(CreateTaxThresholdRequest request){
        var newTaxThreshold = new TaxThreshold(request.taxYear(), ThresholdType.valueOf(request.type()), request.annualIncomeLimit(),request.monthlyPayment(),request.validFrom(),request.validTo());
        var saved = repository.save(newTaxThreshold);
        return toResponse(saved);

    }

    @Transactional(readOnly = true)
    public TaxThresholdResponse findByTaxYearAndType(int taxYear, String type){
        var taxThreshold = repository.findByTaxYearAndType(taxYear, ThresholdType.valueOf(type)).orElseThrow(()->new EntityNotFoundException("Cannot find Entity with type "+type+" and year "+taxYear));
        return toResponse(taxThreshold);

    }
    @Transactional(readOnly = true)
    public List<TaxThresholdResponse> findByTaxYear(int taxYear){
        var taxThresholds = repository.findByTaxYear(taxYear);
        return taxThresholds.stream().map(this::toResponse).toList();
    }

    private TaxThresholdResponse toResponse(TaxThreshold taxThreshold){
        return new TaxThresholdResponse(taxThreshold.getId(), taxThreshold.getTaxYear(),taxThreshold.getType().name(),taxThreshold.getAnnualIncomeLimit(),
                taxThreshold.getMonthlyPayment(),taxThreshold.getValidFrom(),taxThreshold.getValidTo(), taxThreshold.getCreatedAt(), taxThreshold.getUpdatedAt());
    }


}
