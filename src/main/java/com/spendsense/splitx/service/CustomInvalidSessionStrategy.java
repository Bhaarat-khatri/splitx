package com.spendsense.splitx.service;

import java.io.IOException;

import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class CustomInvalidSessionStrategy implements InvalidSessionStrategy {

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Custom response when session expires
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // Set HTTP 401 Unauthorized
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"Session expired\", \"message\": \"Your session has expired, please log in again.\"}");
    }
}
