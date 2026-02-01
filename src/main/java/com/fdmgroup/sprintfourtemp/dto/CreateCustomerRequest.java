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
    
    @NotBlank(message = "Customer type is required and cannot be blank")
    @Schema(description = "Type of customer - either PERSON or COMPANY", allowableValues = {"PERSON", "COMPANY"})
    private String customerType;
    
    public CreateCustomerRequest() {
    }
    
    public CreateCustomerRequest(String name, String streetNumber, String postalCode, String customerType) {
        this.name = name;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.customerType = customerType;
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
    
    public String getCustomerType() {
        return customerType;
    }
    
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
}
