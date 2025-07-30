package com.payment.paymentService.controllerAdvice;

import brave.Span;
import brave.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TracingExceptionHandler {

    @Autowired
    private Tracer tracer;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(Exception e) {
        Span span = tracer.currentSpan();
        if (span != null) {
            span.tag("error", e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + e.getMessage());
    }
}
