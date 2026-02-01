package com.fdmgroup.sprintfourtemp.dto;

import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request object for creating a new customer")
public class CreateCustomerRequest {
	
	@NotBlank(message = "Name is required and cannot be blank")
    private String name;
    
    @NotBlank(message = "Street number is required and cannot be blank")
    private String streetNumber;
    
    @NotBlank(message = "Postal code is required and cannot be blank")
    private String postalCode;
    
    public CreateCustomerRequest() {
    }
    
    public CreateCustomerRequest(String name, String streetNumber, String postalCode) {
        this.name = name;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getStreetNumber() {
        return streetNumber;
    }
    
    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }
    
    public String getPostalCode() {
        return postalCode;
    }
    
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
