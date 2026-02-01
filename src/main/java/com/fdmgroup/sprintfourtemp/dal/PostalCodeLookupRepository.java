package com.fdmgroup.sprintfourtemp.dal;

import com.fdmgroup.sprintfourtemp.model.PostalCodeLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PostalCodeLookupRepository extends JpaRepository<PostalCodeLookup, Long> {
	
	// Find by postal code prefix (first 3 characters)
    Optional<PostalCodeLookup> findByPostalCodePrefix(String postalCodePrefix);
}
