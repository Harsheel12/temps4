package com.fdmgroup.sprintfourtemp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.sprintfourtemp.dal.CompanyRepository;
import com.fdmgroup.sprintfourtemp.dal.CustomerRepository;
import com.fdmgroup.sprintfourtemp.dal.PersonRepository;
import com.fdmgroup.sprintfourtemp.dal.PostalCodeLookupRepository;
import com.fdmgroup.sprintfourtemp.dto.CreateCustomerRequest;
import com.fdmgroup.sprintfourtemp.dto.UpdateAddressRequest;
import com.fdmgroup.sprintfourtemp.dto.UpdateNameRequest;
import com.fdmgroup.sprintfourtemp.exception.CustomerNotFoundException;
import com.fdmgroup.sprintfourtemp.exception.InvalidPostalCodeException;
import com.fdmgroup.sprintfourtemp.model.Address;
import com.fdmgroup.sprintfourtemp.model.Company;
import com.fdmgroup.sprintfourtemp.model.Customer;
import com.fdmgroup.sprintfourtemp.model.Person;
import com.fdmgroup.sprintfourtemp.model.PostalCodeLookup;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
    private PostalCodeLookupRepository postalCodeLookupRepository;
	
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private CompanyRepository companyRepository;
	
	public Customer createCustomer(CreateCustomerRequest request) {
		// Extract postal code prefix (first 3 characters, remove spaces)
        String postalCode = request.getPostalCode().replace(" ", "").toUpperCase();
        String postalCodePrefix = postalCode.substring(0, 3);
        
        // Lookup city and province
        PostalCodeLookup lookup = postalCodeLookupRepository
                .findByPostalCodePrefix(postalCodePrefix)
                .orElseThrow(() -> new InvalidPostalCodeException(request.getPostalCode()));
        
        // Create address with looked up values
        Address address = new Address(
            request.getStreetNumber(),
            request.getPostalCode(),
            lookup.getCity(),
            lookup.getProvince()
        );
        
        // Create customer based on type
        Customer customer;
        if ("PERSON".equalsIgnoreCase(request.getCustomerType())) {
            customer = new Person(request.getName(), address);
            return personRepository.save((Person) customer);
        } else if ("COMPANY".equalsIgnoreCase(request.getCustomerType())) {
            customer = new Company(request.getName(), address);
            return companyRepository.save((Company) customer);
        } else {
            throw new IllegalArgumentException("Invalid customer type: " + request.getCustomerType());
        }
        
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
        
        // Update the address fields
        Address address = customer.getAddress();
        if (address == null) {
            address = new Address();
            customer.setAddress(address);
        }
        
        address.setPostalCode(request.getPostalCode());
        address.setCity(request.getCity());
        address.setProvince(request.getProvince());
        
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
	
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
    
    /**
     * Get all companies only
     */
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

}
