package com.vektorshield.controller;

import com.vektorshield.record.GatewayRequest;
import com.vektorshield.record.GatewayResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/shield")
public class PromptIngestionController {

    private static final Logger log = LoggerFactory.getLogger(PromptIngestionController.class);

    @PostMapping("/analyze")
    public ResponseEntity<GatewayResponse> ingestAndAnalyze(@Valid @RequestBody GatewayRequest request) {

        // Upgraded to SLF4J: Explicitly logs the Virtual Thread handle for concurrency auditing
        log.info("--> Ingesting prompt request on execution thread: {}", Thread.currentThread());

        // TODO: Replace this mock scaffold with dynamic calculations from VectorAnalysisService output
        GatewayResponse mockResponse = new GatewayResponse(
                request.requestId() != null ? request.requestId() : UUID.randomUUID().toString(),
                false,
                "NONE",
                0.85,
                "Prompt authorized by VektorShield control plane."
        );

        return ResponseEntity.ok(mockResponse);
    }
}