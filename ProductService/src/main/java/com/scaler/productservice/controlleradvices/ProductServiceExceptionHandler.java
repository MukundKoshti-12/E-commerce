package com.scaler.productservice.controlleradvices;

import com.scaler.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // This defines a Controller Advice, Controller Advice means it is a layer between controller and customer which returns the response from controller to customer with modifications when required
public class ProductServiceExceptionHandler {
    @ExceptionHandler(RuntimeException.class) // Defines that below class is a Exception Handler
    public ResponseEntity<Void> handleRunTimeException() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException() {
        return new ResponseEntity<>(
                "Global Exception Handler",
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException productNotFoundException) {
        return new ResponseEntity<>(
                productNotFoundException.getProductId() + " is an invalid product id, please try with a valid product id",
                HttpStatus.NOT_FOUND);
    }
}
