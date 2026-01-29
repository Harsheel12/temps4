package com.fdmgroup.sprintfourtemp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.sprintfourtemp.model.Customer;
import com.fdmgroup.sprintfourtemp.service.CustomerService;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	private CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping
	public List<Customer> getAllCustomers() {
		return customerService.getCustomers();
	}
	
	@GetMapping("/{customerId}")
	public Customer getCustomerById(@PathVariable int customerId) {
		return customerService.findById(customerId);
	}
	
	@PostMapping
	public void createCustomer(@RequestBody Customer customer) {
		this.customerService.createCustomer(customer);
	}
	
	@DeleteMapping ("/{customerId}")
	public void deleteUserById(@PathVariable int customerId) {
		customerService.deleteById(customerId);
	}

}
