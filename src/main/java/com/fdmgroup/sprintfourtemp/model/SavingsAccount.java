package com.fdmgroup.sprintfourtemp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "savings_account")
@PrimaryKeyJoinColumn(name = "account_id")  // Foreign key to Account table
@Schema(description = "Savings account with interest rate")
public class SavingsAccount extends Account {
	
    @Min(value = 0, message = "Interest rate cannot be negative")
    @Column(name = "interest_rate")
    private double interestRate;
    
    public SavingsAccount() {
        super();
    }
    
    public SavingsAccount(double balance, Customer customer, double interestRate) {
        super(balance, customer);
        this.interestRate = interestRate;
    }
    
    public double getInterestRate() {
        return interestRate;
    }
    
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    
    @Override
    public String getAccountType() {
        return "SAVINGS";
    }
}
