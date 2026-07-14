package com.vektorshield.record;

public record GatewayRequest(
        String requestId,
        String userId,
        String promptPayload,
        long timestamp
) {}