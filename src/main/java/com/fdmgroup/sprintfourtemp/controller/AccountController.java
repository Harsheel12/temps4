package com.fdmgroup.sprintfourtemp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    public ResponseEntity<Account> getAccountById(
            @Parameter(description = "ID of the account to retrieve", required = true)
            @PathVariable Long id) {
        Account account = accountService.getAccountById(id);
        return ResponseEntity.ok(account);
    }
    
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }
    
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Account>> getAccountsByCustomerId(
            @Parameter(description = "ID of the customer", required = true)
            @PathVariable Long customerId) {
        List<Account> accounts = accountService.getAccountsByCustomerId(customerId);
        return ResponseEntity.ok(accounts);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(
            @Parameter(description = "ID of the account to delete", required = true)
            @PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
