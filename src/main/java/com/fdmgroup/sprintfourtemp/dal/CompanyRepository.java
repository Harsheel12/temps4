package com.fdmgroup.sprintfourtemp.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fdmgroup.sprintfourtemp.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
