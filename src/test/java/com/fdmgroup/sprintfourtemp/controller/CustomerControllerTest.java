package com.fdmgroup.sprintfourtemp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdmgroup.sprintfourtemp.dal.CustomerRepository;
import com.fdmgroup.sprintfourtemp.dto.CreateCustomerRequest;
import com.fdmgroup.sprintfourtemp.model.Customer;
import com.fdmgroup.sprintfourtemp.model.Person;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class CustomerControllerTest {
	
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @BeforeEach
    public void setUp() {
        // Clean up database before each test
        customerRepository.deleteAll();
    }
    
    @Test
    public void testCreateCustomer_Success() throws Exception {
        // Arrange
        CreateCustomerRequest request = new CreateCustomerRequest(
            "John Doe",
            "123",
            "M5H 2N2",
            "PERSON"
        );
        
        // Act & Assert
        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customerId").exists())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.address.streetNumber").value("123"))
                .andExpect(jsonPath("$.address.postalCode").value("M5H 2N2"))
                .andExpect(jsonPath("$.address.city").value("Toronto"))
                .andExpect(jsonPath("$.address.province").value("Ontario"))
                .andExpect(jsonPath("$.customerType").value("PERSON"));
    }
    
    @Test
    public void testCreateCustomer_InvalidPostalCode() throws Exception {
        // Arrange
        CreateCustomerRequest request = new CreateCustomerRequest(
            "John Doe",
            "123",
            "INVALID",
            "PERSON"
        );
        
        // Act & Assert
        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));
    }
    
    @Test
    public void testCreateCustomer_UnsupportedPostalCode() throws Exception {
        // Arrange
        CreateCustomerRequest request = new CreateCustomerRequest(
            "John Doe",
            "123",
            "X9X 9X9",
            "PERSON"
        );
        
        // Act & Assert
        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Postal Code"));
    }
    
    @Test
    public void testGetCustomerById_Success() throws Exception {
        // Arrange - Create a customer first
        CreateCustomerRequest request = new CreateCustomerRequest(
            "Jane Smith",
            "456",
            "K1A 0B1",
            "PERSON"
        );
        
        String response = mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        
        Customer createdCustomer = objectMapper.readValue(response, Person.class);
        Long customerId = createdCustomer.getCustomerId();
        
        // Act & Assert
        mockMvc.perform(get("/api/customers/" + customerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(customerId))
                .andExpect(jsonPath("$.name").value("Jane Smith"))
                .andExpect(jsonPath("$.address.city").value("Ottawa"));
    }
    
    @Test
    public void testGetCustomerById_NotFound() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/customers/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message").value("Customer not found with id: 999"));
    }
}
