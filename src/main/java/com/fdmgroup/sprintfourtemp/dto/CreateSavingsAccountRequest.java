package com.fdmgroup.sprintfourtemp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request object for creating a new savings account")
public class CreateSavingsAccountRequest {
	
    @NotNull(message = "Customer ID is required")
    private Long customerId;
    
    @Min(value = 0, message = "Initial balance cannot be negative")
    private double balance;
    
    @Min(value = 0, message = "Interest rate cannot be negative")
    private double interestRate;
    
    public CreateSavingsAccountRequest() {
    }
    
    public CreateSavingsAccountRequest(Long customerId, double balance, double interestRate) {
        this.customerId = customerId;
        this.balance = balance;
        this.interestRate = interestRate;
    }
    
    public Long getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public double getInterestRate() {
        return interestRate;
    }
    
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
