package com.fdmgroup.sprintfourtemp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "customers")
@Schema(description = "Customer entity representing a bank customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private long customerId;
	
	@NotBlank(message = "Name is required and cannot be blank")
	private String name;
	
	@NotBlank(message = "Street number is required and cannot be blank")
	private String streetNumber;
	
	@NotBlank(message = "City is required and cannot be blank")
	private String city;
	
	@NotBlank(message = "Province is required and cannot be blank")
	private String province;
	
	@NotBlank(message = "Postal code is required and cannot be blank")
	private String postalCode;
	
	public Customer() {
		
	}
	
	public Customer(String name, String streetNumber, String city, String province, String postalCode) {
		this.name = name;
		this.streetNumber = streetNumber;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
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

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
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

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
}
