package com.fdmgroup.sprintfourtemp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	/**
     * Handle CustomerNotFoundException
     */
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(
            CustomerNotFoundException ex, WebRequest request) {
        
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            "Not Found",
            ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    
    /**
     * Handle validation errors (Jakarta Validation)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        
        List<String> validationErrors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            validationErrors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Validation Failed",
            "Invalid input data",
            request.getDescription(false).replace("uri=", ""),
            validationErrors
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
    /**
     * Handle InvalidPostalCodeException
     */
    @ExceptionHandler(InvalidPostalCodeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPostalCodeException(
            InvalidPostalCodeException ex, WebRequest request) {
        
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Invalid Postal Code",
            ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
    /**
     * Handle AccountNotFoundException
     */
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAccountNotFoundException(
            AccountNotFoundException ex, WebRequest request) {
        
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            "Not Found",
            ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    
    /**
     * Handle all other exceptions
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {
        
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal Server Error",
            ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
