package com.fdmgroup.sprintfourtemp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "addresses")
@Schema(description = "Address entity representing a customer's address")
public class Address {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    
    @NotBlank(message = "Street number is required and cannot be blank")
    private String streetNumber;
    
    @NotBlank(message = "Postal code is required and cannot be blank")
    private String postalCode;
    
    @NotBlank(message = "City is required and cannot be blank")
    private String city;
    
    @NotBlank(message = "Province is required and cannot be blank")
    private String province;
    
    @OneToOne(mappedBy = "address")
    @JsonIgnore  // Prevents infinite recursion in JSON serialization
    private Customer customer;
    
    public Address() {
    }
    
    public Address(String streetNumber, String postalCode, String city, String province) {
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.province = province;
    }
    
    public Long getAddressId() {
        return addressId;
    }
    
    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
    
    public String getStreetNumber() {
        return streetNumber;
    }
    
    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }
    
    public String getPostalCode() {
        return postalCode;
    }
    
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getProvince() {
        return province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
