package com.fdmgroup.sprintfourtemp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fdmgroup.sprintfourtemp.dal.AccountRepository;
import com.fdmgroup.sprintfourtemp.dal.CheckingAccountRepository;
import com.fdmgroup.sprintfourtemp.dal.CustomerRepository;
import com.fdmgroup.sprintfourtemp.dal.SavingsAccountRepository;
import com.fdmgroup.sprintfourtemp.dto.CreateCheckingAccountRequest;
import com.fdmgroup.sprintfourtemp.dto.CreateSavingsAccountRequest;
import com.fdmgroup.sprintfourtemp.exception.AccountNotFoundException;
import com.fdmgroup.sprintfourtemp.exception.CustomerNotFoundException;
import com.fdmgroup.sprintfourtemp.model.Account;
import com.fdmgroup.sprintfourtemp.model.CheckingAccount;
import com.fdmgroup.sprintfourtemp.model.Customer;
import com.fdmgroup.sprintfourtemp.model.SavingsAccount;

import java.util.List;

@Service
public class AccountService {
	
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private SavingsAccountRepository savingsAccountRepository;
    
    @Autowired
    private CheckingAccountRepository checkingAccountRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Transactional
    public SavingsAccount createSavingsAccount(CreateSavingsAccountRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException(request.getCustomerId()));
        
        SavingsAccount account = new SavingsAccount(
            request.getBalance(),
            customer,
            request.getInterestRate()
        );
        
        customer.addAccount(account);
        return savingsAccountRepository.save(account);
    }
    
    @Transactional
    public CheckingAccount createCheckingAccount(CreateCheckingAccountRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException(request.getCustomerId()));
        
        CheckingAccount account = new CheckingAccount(
            request.getBalance(),
            customer,
            request.getNextCheckNumber()
        );
        
        customer.addAccount(account);
        return checkingAccountRepository.save(account);
    }
    
    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }
    
    public List<Account> getAccountsByCustomerId(Long customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundException(customerId);
        }
        return accountRepository.findByCustomerCustomerId(customerId);
    }
    
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
    
    @Transactional
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        
        Customer customer = account.getCustomer();
        customer.removeAccount(account);
        accountRepository.delete(account);
    }
    
    public List<Account> getAccountsByCity(String city) {
        return accountRepository.findByCustomerAddressCity(city);
    }
}
