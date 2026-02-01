package com.fdmgroup.sprintfourtemp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fdmgroup.sprintfourtemp.dto.CreateCheckingAccountRequest;
import com.fdmgroup.sprintfourtemp.dto.CreateSavingsAccountRequest;
import com.fdmgroup.sprintfourtemp.model.Account;
import com.fdmgroup.sprintfourtemp.model.CheckingAccount;
import com.fdmgroup.sprintfourtemp.model.SavingsAccount;
import com.fdmgroup.sprintfourtemp.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Account Management", description = "APIs for managing bank accounts (Savings and Checking)")
public class AccountController {
	
    @Autowired
    private AccountService accountService;
    
    @PostMapping("/savings")
    public ResponseEntity<SavingsAccount> createSavingsAccount(
            @Valid @RequestBody CreateSavingsAccountRequest request) {
        SavingsAccount account = accountService.createSavingsAccount(request);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }
    
    @PostMapping("/checking")
    public ResponseEntity<CheckingAccount> createCheckingAccount(
            @Valid @RequestBody CreateCheckingAccountRequest request) {
        CheckingAccount account = accountService.createCheckingAccount(request);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);
        return ResponseEntity.ok(account);
    }
    
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }
    
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Account>> getAccountsByCustomerId(@PathVariable Long customerId) {
        List<Account> accounts = accountService.getAccountsByCustomerId(customerId);
        return ResponseEntity.ok(accounts);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/city/{city}")
    public ResponseEntity<List<Account>> getAccountsByCity(@PathVariable String city) {
        List<Account> accounts = accountService.getAccountsByCity(city);
        return ResponseEntity.ok(accounts);
    }
}
