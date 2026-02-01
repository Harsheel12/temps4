package com.fdmgroup.sprintfourtemp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.sprintfourtemp.dal.CustomerRepository;
import com.fdmgroup.sprintfourtemp.dal.PostalCodeLookupRepository;
import com.fdmgroup.sprintfourtemp.dto.CreateCustomerRequest;
import com.fdmgroup.sprintfourtemp.dto.UpdateAddressRequest;
import com.fdmgroup.sprintfourtemp.dto.UpdateNameRequest;
import com.fdmgroup.sprintfourtemp.exception.CustomerNotFoundException;
import com.fdmgroup.sprintfourtemp.exception.InvalidPostalCodeException;
import com.fdmgroup.sprintfourtemp.model.Customer;
import com.fdmgroup.sprintfourtemp.model.PostalCodeLookup;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
    private PostalCodeLookupRepository postalCodeLookupRepository;
	
	public Customer createCustomer(CreateCustomerRequest request) {
		// Extract postal code prefix (first 3 characters, remove spaces)
        String postalCode = request.getPostalCode().replace(" ", "").toUpperCase();
        String postalCodePrefix = postalCode.substring(0, 3);
        
        // Lookup city and province
        PostalCodeLookup lookup = postalCodeLookupRepository
                .findByPostalCodePrefix(postalCodePrefix)
                .orElseThrow(() -> new InvalidPostalCodeException(request.getPostalCode()));
        
        // Create customer with looked up values
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setStreetNumber(request.getStreetNumber());
        customer.setPostalCode(request.getPostalCode());
        customer.setCity(lookup.getCity());
        customer.setProvince(lookup.getProvince());
        
        return customerRepository.save(customer);
    }
	
	public Customer findCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }
	
	public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
	
	public Customer updateCustomerName(Long id, UpdateNameRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        
        customer.setName(request.getName());
        return customerRepository.save(customer);
    }
	
	public Customer updateCustomerAddress(Long id, UpdateAddressRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        
        customer.setPostalCode(request.getPostalCode());
        customer.setCity(request.getCity());
        customer.setProvince(request.getProvince());
        return customerRepository.save(customer);
    }
	
	public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException(id);
        }
        customerRepository.deleteById(id);
    }
	
	public List<PostalCodeLookup> getAllPostalCodeLookups() {
        return postalCodeLookupRepository.findAll();
    }

}
