package com.fdmgroup.sprintfourtemp.exception;

public class InvalidPostalCodeException extends RuntimeException {
	
	public InvalidPostalCodeException(String postalCode) {
        super("Invalid or unsupported postal code: " + postalCode + ". City and province not found.");
    }
    
    public InvalidPostalCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
