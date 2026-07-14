package com.vektorshield.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Component
public class PromptMetricsAspect {

    private static final Logger log = LoggerFactory.getLogger(PromptMetricsAspect.class);

    @Around("execution(* com.vektorshield.controller.PromptIngestionController.ingestAndAnalyze(..))")
    public Object profileLatency(ProceedingJoinPoint joinPoint) throws Throwable {
        Instant start = Instant.now();

        try {
            return joinPoint.proceed();
        } finally {
            Instant finish = Instant.now();
            long microseconds = Duration.between(start, finish).toNanos() / 1000;

            // Upgraded to SLF4J Logger for enterprise performance profiling metrics
            log.info("--> [METRIC-PROXY] Low-latency ingestion completed in: {} μs", microseconds);
        }
    }
}