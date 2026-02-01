package com.fdmgroup.sprintfourtemp.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@DiscriminatorValue("COMPANY")
@Schema(description = "Company customer entity representing a business customer")
public class Company extends Customer {
	
    public Company() {
        super();
    }
    
    public Company(String name, Address address) {
        super(name, address);
    }
    
    @Override
    public String getCustomerType() {
        return "COMPANY";
    }
}
