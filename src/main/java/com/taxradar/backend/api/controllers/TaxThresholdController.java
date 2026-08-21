package com.taxradar.backend.api.controllers;
import com.taxradar.backend.application.cases.taxthreshold.commands.CreateTaxThresholdRequest;
import com.taxradar.backend.application.cases.taxthreshold.commands.TaxThresholdResponse;
import com.taxradar.backend.application.cases.taxthreshold.services.TaxThresholdService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/tax-thresholds")
public class TaxThresholdController {

    private final TaxThresholdService service;

    public TaxThresholdController(TaxThresholdService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TaxThresholdResponse> createTaxThreshold(@RequestBody @Valid CreateTaxThresholdRequest request){
        var response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<TaxThresholdResponse> getByTaxYearAndType(@RequestParam Integer taxYear, @RequestParam String type){
        var response = service.findByTaxYearAndType(taxYear, type);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/{taxyear}")
    public ResponseEntity<List<TaxThresholdResponse>> getByTaxYear(@PathVariable Integer taxyear){
        var response = service.findByTaxYear(taxyear);
        return ResponseEntity.ok(response);

    }

}
