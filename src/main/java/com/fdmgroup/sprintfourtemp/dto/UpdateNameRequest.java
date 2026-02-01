package com.fdmgroup.sprintfourtemp.dto;

import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request object for updating customer name")
public class UpdateNameRequest {
	
	@NotBlank(message = "Name cannot be blank")
    @Schema(description = "New name for the customer", example = "John Michael Doe")
    private String name;
    
    public UpdateNameRequest() {
    }
    
    public UpdateNameRequest(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
