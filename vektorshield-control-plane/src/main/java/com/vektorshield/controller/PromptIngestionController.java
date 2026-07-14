package com.vektorshield.controller;

import com.vektorshield.record.GatewayRequest;
import com.vektorshield.record.GatewayResponse;
import com.vektorshield.service.VectorAnalysisService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shield")
public class PromptIngestionController {

    private static final Logger log = LoggerFactory.getLogger(PromptIngestionController.class);
    private final VectorAnalysisService vectorAnalysisService;

    // Dependency Injection via constructor wiring
    public PromptIngestionController(VectorAnalysisService vectorAnalysisService) {
        this.vectorAnalysisService = vectorAnalysisService;
    }

    @PostMapping("/analyze")
    public ResponseEntity<GatewayResponse> ingestAndAnalyze(@Valid @RequestBody GatewayRequest request) {
        log.info("--> Ingesting prompt request on execution thread: {}", Thread.currentThread());

        // Fully connected to the service layer pipeline
        GatewayResponse response = vectorAnalysisService.broadcastToPythonEngine(request);

        return ResponseEntity.ok(response);
    }
}