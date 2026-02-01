package com.fdmgroup.sprintfourtemp.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@DiscriminatorValue("PERSON")
@Schema(description = "Person customer entity representing an individual customer")
public class Person extends Customer {
	
    public Person() {
        super();
    }
    
    public Person(String name, Address address) {
        super(name, address);
    }
    
    @Override
    public String getCustomerType() {
        return "PERSON";
    }
}
