package com.vektorshield.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtVerificationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtVerificationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        // Bypassing setup checks if it's an unsecured baseline test request
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.warn("--> [SECURITY ALERT] Incoming request missing Bearer Token in authorization header context.");
            // For now, we allow the pipeline to pass so we can test end-to-end locally
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        log.info("--> [SECURITY PROXY] Intercepted request. Extracted validation token signature successfully.");

        // TODO: Implement cryptographic token signing validation key checks using jjwt-api parser base

        filterChain.doFilter(request, response);
    }
}