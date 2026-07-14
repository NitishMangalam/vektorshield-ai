package com.vektorshield.controller;

import com.vektorshield.record.GatewayRequest;
import com.vektorshield.record.GatewayResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/shield")
public class PromptIngestionController {

    @PostMapping("/analyze")
    public ResponseEntity<GatewayResponse> ingestAndAnalyze(@RequestBody GatewayRequest request) {
        // Log the active execution thread to verify Virtual Threads in the terminal later
        System.out.println("--> Ingesting prompt on execution thread: " + Thread.currentThread());

        // Baseline mocking layer until the Python AI plane connects
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