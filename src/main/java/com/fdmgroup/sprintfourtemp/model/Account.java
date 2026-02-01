package com.fdmgroup.sprintfourtemp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.JOINED)
@Schema(description = "Abstract account entity. Can be either a Savings or Checking account")
public abstract class Account {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long accountId;
    
    @Min(value = 0, message = "Balance cannot be negative")
    @Column(name = "balance")
    private double balance;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_cust_id", nullable = false)
    @JsonIgnore  // Prevents infinite recursion in JSON serialization
    private Customer customer;
    
    public Account() {
    }
    
    public Account(double balance, Customer customer) {
        this.balance = balance;
        this.customer = customer;
    }
    
    public Long getAccountId() {
        return accountId;
    }
    
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public abstract String getAccountType();
}
