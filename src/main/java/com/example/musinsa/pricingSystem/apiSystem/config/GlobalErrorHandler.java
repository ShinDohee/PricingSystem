package com.example.musinsa.pricingSystem.apiSystem.config;

import com.example.musinsa.pricingSystem.apiSystem.config.PricingSystemException.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalErrorHandler {
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String, Object>> defaultErrorHandler(Exception e) {
        ExceptionCode exceptionCode = ExceptionCode.STATUS_UNKNOWN_ERROR;
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("status", exceptionCode.getCode());
        response.put("msg", exceptionCode.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = PricingSystemException.class)
    public ResponseEntity<Map<String, Object>> errorHandler(PricingSystemException e) {
        ExceptionCode exceptionCode = ExceptionCode.STATUS_BAD_REQUEST;
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("status", exceptionCode.getCode());
        response.put("msg", exceptionCode.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
