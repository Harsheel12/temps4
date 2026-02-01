package com.fdmgroup.sprintfourtemp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "checking_account")
@PrimaryKeyJoinColumn(name = "account_id")  // Foreign key to Account table
@Schema(description = "Checking account with check number tracking")
public class CheckingAccount extends Account {
	
    @Min(value = 1, message = "Next check number must be at least 1")
    @Column(name = "next_check_number")
    private int nextCheckNumber;
    
    public CheckingAccount() {
        super();
    }
    
    public CheckingAccount(double balance, Customer customer, int nextCheckNumber) {
        super(balance, customer);
        this.nextCheckNumber = nextCheckNumber;
    }
    
    public int getNextCheckNumber() {
        return nextCheckNumber;
    }
    
    public void setNextCheckNumber(int nextCheckNumber) {
        this.nextCheckNumber = nextCheckNumber;
    }
    
    @Override
    public String getAccountType() {
        return "CHECKING";
    }
}
