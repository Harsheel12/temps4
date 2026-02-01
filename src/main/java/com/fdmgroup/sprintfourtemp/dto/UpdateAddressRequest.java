package com.fdmgroup.sprintfourtemp.dto;

import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request object for updating customer address")
public class UpdateAddressRequest {
	
	@Schema(description = "New postal code for the customer", example = "M4B 1B3")
    private String postalCode;
    
    @NotBlank(message = "City cannot be blank")
    @Schema(description = "New city for the customer", example = "Mississauga")
    private String city;
    
    @NotBlank(message = "Province cannot be blank")
    @Schema(description = "New province for the customer", example = "Ontario")
    private String province;
    
    public UpdateAddressRequest() {
    }
    
    public UpdateAddressRequest(String postalCode, String city, String province) {
        this.postalCode = postalCode;
        this.city = city;
        this.province = province;
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
}
