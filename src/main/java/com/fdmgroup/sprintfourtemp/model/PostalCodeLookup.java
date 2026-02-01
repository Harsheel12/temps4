package com.fdmgroup.sprintfourtemp.model;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "postal_code_lookup")
@Schema(description = "Lookup table for postal codes to city and province mapping")
public class PostalCodeLookup {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    @Schema(description = "Postal code prefix (first 3 characters)", example = "M5H")
    private String postalCodePrefix;
    
    @Column(nullable = false)
    @Schema(description = "City name", example = "Toronto")
    private String city;
    
    @Column(nullable = false)
    @Schema(description = "Province name", example = "Ontario")
    private String province;
    
    public PostalCodeLookup() {
    }
    
    public PostalCodeLookup(String postalCodePrefix, String city, String province) {
        this.postalCodePrefix = postalCodePrefix;
        this.city = city;
        this.province = province;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getPostalCodePrefix() {
        return postalCodePrefix;
    }
    
    public void setPostalCodePrefix(String postalCodePrefix) {
        this.postalCodePrefix = postalCodePrefix;
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
