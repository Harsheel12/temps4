package com.fdmgroup.sprintfourtemp.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.fdmgroup.sprintfourtemp.model.Account;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
    // Find all accounts for a specific customer
    List<Account> findByCustomerCustomerId(Long customerId);
    
    // Find all accounts for customers in a specific city
    @Query("SELECT a FROM Account a WHERE a.customer.address.city = :city")
    List<Account> findByCustomerAddressCity(@Param("city") String city);
}
