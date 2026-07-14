package com.vektorshield.record;

public record GatewayResponse(
        String requestId,
        boolean isBlocked,
        String riskCategory,
        double dynamicThreshold,
        String mitigationMessage
) {}