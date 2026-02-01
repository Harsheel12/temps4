package com.fdmgroup.sprintfourtemp.dal;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fdmgroup.sprintfourtemp.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
