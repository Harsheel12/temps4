package com.fdmgroup.sprintfourtemp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.sprintfourtemp.dto.CreateCustomerRequest;
import com.fdmgroup.sprintfourtemp.dto.UpdateAddressRequest;
import com.fdmgroup.sprintfourtemp.dto.UpdateNameRequest;
import com.fdmgroup.sprintfourtemp.model.Customer;
import com.fdmgroup.sprintfourtemp.model.PostalCodeLookup;
import com.fdmgroup.sprintfourtemp.service.CustomerService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer Management", description = "APIs for managing bank customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping
    public ResponseEntity<Customer> createCustomer(
            @Valid @RequestBody Customer customer) {
        Customer savedCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }
	
	@PostMapping("/code")
    public ResponseEntity<Customer> createCustomerWithPostalCode(
            @Valid @RequestBody CreateCustomerRequest request) {
        Customer savedCustomer = customerService.createCustomerWithPostalCode(request);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
		Customer customer = customerService.findCustomerById(id);
        return ResponseEntity.ok(customer);
	}
	
	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }
	
	@PatchMapping("/{id}/name")
	public ResponseEntity<Customer> updateCustomerName(
			@PathVariable Long id,
            @Valid @RequestBody UpdateNameRequest request) {
		Customer updatedCustomer = customerService.updateCustomerName(id, request);
        return ResponseEntity.ok(updatedCustomer);
	}
	
	@PatchMapping("/{id}/address")
	public ResponseEntity<Customer> updateCustomerAddress(
			@PathVariable Long id,
            @Valid @RequestBody UpdateAddressRequest request) {
		Customer updatedCustomer = customerService.updateCustomerAddress(id, request);
        return ResponseEntity.ok(updatedCustomer);
	}
	
	@DeleteMapping ("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/postal-codes")
	public ResponseEntity<List<PostalCodeLookup>> getAllPostalCodeLookups() {
	    List<PostalCodeLookup> lookups = customerService.getAllPostalCodeLookups();
	    return ResponseEntity.ok(lookups);
	}

}
