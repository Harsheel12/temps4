package com.fdmgroup.sprintfourtemp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "customers")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "customer_type", discriminatorType = DiscriminatorType.STRING)
@Schema(description = "Abstract customer entity. Can be either a Person or Company")
public abstract class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private long customerId;
	
	@NotBlank(message = "Name is required and cannot be blank")
	@Column(name = "customer_name")
	private String name;
	
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_address_id", referencedColumnName = "addressId")
	private Address address;
	
	public Customer() {
		
	}
	
    public Customer(String name, Address address) {
        this.name = name;
        this.address = address;
    }
	
	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
    public Address getAddress() {
        return address;
    }
    
    public void setAddress(Address address) {
        this.address = address;
    }
    
    public abstract String getCustomerType();
}
