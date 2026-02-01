package com.fdmgroup.sprintfourtemp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request object for creating a new checking account")
public class CreateCheckingAccountRequest {
	
    @NotNull(message = "Customer ID is required")
    private Long customerId;
    
    @Min(value = 0, message = "Initial balance cannot be negative")
    private double balance;
    
    @Min(value = 1, message = "Next check number must be at least 1")
    private int nextCheckNumber;
    
    public CreateCheckingAccountRequest() {
    }
    
    public CreateCheckingAccountRequest(Long customerId, double balance, int nextCheckNumber) {
        this.customerId = customerId;
        this.balance = balance;
        this.nextCheckNumber = nextCheckNumber;
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
    
    public int getNextCheckNumber() {
        return nextCheckNumber;
    }
    
    public void setNextCheckNumber(int nextCheckNumber) {
        this.nextCheckNumber = nextCheckNumber;
    }
}
