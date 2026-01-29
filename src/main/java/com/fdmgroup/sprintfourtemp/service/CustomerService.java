package com.fdmgroup.sprintfourtemp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.sprintfourtemp.dal.CustomerRepository;
import com.fdmgroup.sprintfourtemp.model.Customer;

@Service
public class CustomerService {

	private CustomerRepository customerRepo;

	@Autowired
	public CustomerService(CustomerRepository customerRepo) {
		super();
		this.customerRepo = customerRepo;
	}

	public List<Customer> getCustomers() {
		return customerRepo.findAll();
	}

	public void createCustomer(Customer customer) {

		Customer newCustomer = new Customer(
				customer.getName(), 
				customer.getStreetNumber(), 
				customer.getProvince(),
				customer.getPostalCode(), 
				customer.getCity()
				);

		if (customer.getName().equals("")) {
			throw new RuntimeException("Invalid username");
		}

		customerRepo.save(newCustomer);
	}
	
	public Customer findById(int id) {
		return customerRepo.findById(id)
				.orElseThrow(()->new RuntimeException("Customer can't be found"));
	}
	
	public void deleteById(int id) {
		customerRepo.deleteById(id);
	}

}
