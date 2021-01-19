package com.upgrad.eshop.aspects;

import com.upgrad.eshop.exceptions.ShippingAddressNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ShippingExceptionHandler {

    @ExceptionHandler(ShippingAddressNotFoundException.class)
    public ResponseEntity<?> handleShippingAddressNotFoundException(Exception e) {
        log.debug(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
