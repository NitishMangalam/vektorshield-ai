package com.vektorshield.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Immutable data contract for incoming LLM prompts entering the gateway.
 * Upgraded with enterprise-grade validation constraints.
 */
public record GatewayRequest(
        String requestId,

        @NotBlank(message = "User identity context cannot be blank")
        String userId,

        @NotBlank(message = "Prompt payload content cannot be empty or blank")
        String promptPayload,

        @NotNull(message = "Timestamp context is required")
        Long timestamp
) {}