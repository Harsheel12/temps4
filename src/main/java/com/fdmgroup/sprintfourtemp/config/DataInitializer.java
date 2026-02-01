package com.fdmgroup.sprintfourtemp.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.fdmgroup.sprintfourtemp.dal.PostalCodeLookupRepository;
import com.fdmgroup.sprintfourtemp.model.PostalCodeLookup;

@Component
public class DataInitializer implements CommandLineRunner {
	
	@Autowired
    private PostalCodeLookupRepository postalCodeLookupRepository;
	
	@Override
    public void run(String... args) throws Exception {
        // Check if data already exists
        if (postalCodeLookupRepository.count() == 0) {
            List<PostalCodeLookup> lookups = Arrays.asList(
                // Ontario
                new PostalCodeLookup("M5H", "Toronto", "Ontario"),
                new PostalCodeLookup("K1A", "Ottawa", "Ontario"),
                new PostalCodeLookup("L5B", "Mississauga", "Ontario"),
                
                // British Columbia
                new PostalCodeLookup("V6B", "Vancouver", "British Columbia"),
                new PostalCodeLookup("V8W", "Victoria", "British Columbia"),
                
                // Quebec
                new PostalCodeLookup("H3A", "Montreal", "Quebec"),
                new PostalCodeLookup("G1R", "Quebec City", "Quebec"),
                
                // Alberta
                new PostalCodeLookup("T2P", "Calgary", "Alberta"),
                new PostalCodeLookup("T5J", "Edmonton", "Alberta"),
                
                // Manitoba
                new PostalCodeLookup("R3C", "Winnipeg", "Manitoba")
            );
            
            postalCodeLookupRepository.saveAll(lookups);
            System.out.println("✅ Postal code lookup data initialized successfully!");
        } else {
            System.out.println("ℹ️ Postal code lookup data already exists. Skipping initialization.");
        }
    }

}
